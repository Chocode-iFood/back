package br.com.chocode.back.services;

import java.util.ArrayList;
import java.util.List;

import br.com.chocode.back.DTO.EntregadorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.chocode.back.dao.EntregadorDAO;
import br.com.chocode.back.model.Entregador;
import br.com.chocode.back.security.ChocodeCrypto;
import br.com.chocode.back.security.Token;
import br.com.chocode.back.security.TokenUtil;

@Component
public class EntregadorServiceImpl implements IEntregadorService {
	private static final Logger LOG = LoggerFactory.getLogger(EntregadorServiceImpl.class);
	private EntregadorDAO dao;

	@Autowired
	public EntregadorServiceImpl(EntregadorDAO dao) {
		this.dao = dao;
	}

	@Override
	public EntregadorDTO save(Entregador entregador) {
		String senhaLogin;
		try {
			LOG.info("Fazendo a criptografia da senha do entregador.");
			senhaLogin = ChocodeCrypto.encrypt(entregador.getSenha());
			entregador.setSenha(senhaLogin);
		} catch (Exception e) {
			LOG.info("Erro na criptografia da senha do entregador.");
			e.printStackTrace();
		}
		LOG.info("Salvando entregador no banco de dados.");
		EntregadorDTO entregadorDTO = new EntregadorDTO(dao.saveAndFlush(entregador));
		return entregadorDTO;
	}

	@Override
	public List<EntregadorDTO> findAll() {
		List<Entregador> listaEntregadores = dao.findAll();
		List<EntregadorDTO> listaEntregadoresDTO = new ArrayList<>();
		for (Entregador entregador : listaEntregadores) {
			listaEntregadoresDTO.add(new EntregadorDTO(entregador));
		}
		LOG.info("Listando todos os entregadores.");
		return listaEntregadoresDTO;
	}
	@Override
	public EntregadorDTO findById(Long id) {
		EntregadorDTO entregadorDTO = new EntregadorDTO(dao.findById(id).get());
		return entregadorDTO;
	}
	@Override
	public Entregador findByIdModel(Long id) {
		LOG.info("Resultado da busca do entregador com o id " + id + ".");
		return dao.findById(id).get();
	}

	@Override
	public Token gerarTokenDeUsuarioLogado(Entregador dadosLogin) {
		Entregador user = dao.findByEmail(dadosLogin.getEmail());
		try {
			if (user != null) {

				String senhaLogin = ChocodeCrypto.encrypt(dadosLogin.getSenha());

				System.out.println("Senha login = " + senhaLogin);
				System.out.println("Senha user  = " + user.getSenha());

				if (senhaLogin.equals(user.getSenha())) {
					LOG.info("Gerando token.");
					return new Token(TokenUtil.createToken(user), user.getId(), user.getNome(), user.getUrlImage());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		LOG.info("Erro ao gerar token.");
		return null;
	}
}
