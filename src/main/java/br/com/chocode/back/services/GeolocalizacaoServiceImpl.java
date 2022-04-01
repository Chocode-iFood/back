package br.com.chocode.back.services;

import br.com.chocode.back.DTO.GeolocalizacaoDTO;
import br.com.chocode.back.dao.GeolocalizacaoDAO;
import br.com.chocode.back.model.Geolocalizacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class GeolocalizacaoServiceImpl implements IGeolocalizacaoService {
	private static final Logger LOG = LoggerFactory.getLogger(GeolocalizacaoServiceImpl.class);
	private GeolocalizacaoDAO dao;
	private IEntregadorService entregadorService;
	private IPedidoService pedidoService;

	@Autowired
	public GeolocalizacaoServiceImpl(GeolocalizacaoDAO dao, IEntregadorService entregadorService, IPedidoService pedidoService) {
		this.dao = dao;
		this.entregadorService = entregadorService;
		this.pedidoService = pedidoService;
	}

	@Override
	public GeolocalizacaoDTO save(GeolocalizacaoDTO geolocalizacaoDTO) {
		Geolocalizacao geolocalizacao = new Geolocalizacao(geolocalizacaoDTO);
		LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		geolocalizacao.setData(now);
		geolocalizacao.setPedido(pedidoService.findById(geolocalizacaoDTO.getIdPedido()));
		if (geolocalizacao == null || geolocalizacao.getPedido().getEntregador() == null ||
				!geolocalizacao.getPedido().getEntregador().getId().equals(geolocalizacaoDTO.getIdEntregador())) {
			LOG.info("Erro ao salvar geolocalizacao.");
			return null;
		}
		geolocalizacao.setEntregador(geolocalizacao.getPedido().getEntregador());
		GeolocalizacaoDTO geolocalizacaoDTO1 = new GeolocalizacaoDTO(dao.saveAndFlush(geolocalizacao));
		LOG.info("Salvando geolocalizacao.");
		return geolocalizacaoDTO1;
	}

	@Override
	public List<GeolocalizacaoDTO> findAll() {
		List<Geolocalizacao> listaGeos = dao.findAll();
		List<GeolocalizacaoDTO> listaGeosDTO = new ArrayList<>();
		for (Geolocalizacao geolocalizacao : listaGeos) {
			listaGeosDTO.add(new GeolocalizacaoDTO(geolocalizacao));
		}
		LOG.info("Listando todas as geolocalizacoes.");
		return listaGeosDTO;
	}

	@Override
	public Geolocalizacao findById(Long id) {
		LOG.info("Resultado da busca de geolocalizacao com o id " + id + ".");
		return dao.findById(id).get();
	}

	@Override
	public List<GeolocalizacaoDTO> findByPedidoId(Long id) {
		List<Geolocalizacao> listaGeo = dao.findByPedidoId(id);
		List<GeolocalizacaoDTO> listaGeoDTO = new ArrayList<>();
		for (Geolocalizacao geolocalizacao:listaGeo) {
			listaGeoDTO.add(new GeolocalizacaoDTO(geolocalizacao));
		}
		LOG.info("Listando todas as geolocalizacoes do pedido com o id " + id + ".");
		return listaGeoDTO;
	}

}
