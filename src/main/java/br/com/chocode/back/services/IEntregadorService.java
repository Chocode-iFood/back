package br.com.chocode.back.services;

import java.util.List;

import br.com.chocode.back.model.Entregador;
import br.com.chocode.back.security.Token;

public interface IEntregadorService {

	public Entregador save(Entregador entregador);

	public List<Entregador> findAll();

	public Entregador findById(Long id);

	public Token gerarTokenDeUsuarioLogado(Entregador dadosLogin);
}