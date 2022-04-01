package br.com.chocode.back.services;

import br.com.chocode.back.DTO.PedidoClienteDTO;
import br.com.chocode.back.DTO.PedidoDTO;
import br.com.chocode.back.dao.PedidoDAO;
import br.com.chocode.back.model.Entregador;
import br.com.chocode.back.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoServiceImpl implements IPedidoService {
	private static final Logger LOG = LoggerFactory.getLogger(PedidoServiceImpl.class);
	private PedidoDAO dao;
	private IEntregadorService entregadorService;
	private IClienteService clienteService;

	@Autowired
	public PedidoServiceImpl(PedidoDAO dao, IEntregadorService entregadorService, IClienteService clienteService) {
		this.dao = dao;
		this.entregadorService = entregadorService;
		this.clienteService = clienteService;
	}

	@Override
	public Pedido save(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido(pedidoDTO, clienteService.findById(pedidoDTO.getIdCliente()));
		LOG.info("Salvando pedido no banco de dados.");
		return dao.saveAndFlush(pedido);
	}

	@Override
	public List<PedidoDTO> findAll() {
		List<Pedido> listaPedidos = dao.findAll(Sort.by(Sort.Direction.ASC, "nomeRestaurante"));
		List<PedidoDTO> listaPedidosDTO = new ArrayList<>();
		for (Pedido pedido : listaPedidos) {
			listaPedidosDTO.add(new PedidoDTO(pedido));
		}
		LOG.info("Listando todos os pedidos.");
		return listaPedidosDTO;
	}

	@Override
	public Pedido findById(Long id) {
		Pedido pedido = dao.findById(id).get();
		LOG.info("Resultado da busca de pedido com o id " + id + ".");
		return pedido;
	}

	@Override
	public PedidoClienteDTO findByIdCliente(Long id) {
		Pedido pedido = findById(id);
		PedidoClienteDTO pedidoClienteDTO = new PedidoClienteDTO(pedido);
		LOG.info("Resultado da busca de pedido(ClienteDTO) com o id " + id + ".");
		return pedidoClienteDTO;
	}

	@Override
	public Pedido saveEntregador(Long idPedido, Long idEntregador) {
		Pedido pedido = findById(idPedido);
		if (pedido == null){
			LOG.info("Pedido (id " + idPedido +
					") não encontrado ao tentar atribuir o entregador (id " + idEntregador + ").");
			return null;
		}
		Entregador entregador = entregadorService.findByIdModel(idEntregador);
		if (entregador == null){
			LOG.info("Entregador (id " + idEntregador +
					") não encontrado ao tentar atribuir ao pedido (id " + idPedido + ").");
			return null;
		}
		pedido.setEntregador(entregador);
		pedido.setStatus("a_caminho");
		LOG.info("Atribuindo o entregador (id " + idEntregador + ") a o pedido (id " + idPedido +
				") e setando o status a_caminho.");
		return dao.saveAndFlush(pedido);
	}

	@Override
	public Pedido statusCancelado(Long idPedido, Long idEntregador) {
		Pedido pedido = findById(idPedido);
		if (pedido == null || pedido.getEntregador() == null ||
				!pedido.getEntregador().getId().equals(idEntregador)) {
			LOG.info("Erro ao tentar setar o status cancelado ao pedido de id " + idPedido +
					", devido o cancelamento do entregador de id " + idEntregador + ".");
			return null;
		}
		pedido.setStatus("cancelado");
		LOG.info("Setando o status cancelado ao pedido de id " + idPedido +
						", devido o cancelamento do entregador de id " + idEntregador + ".");
		return dao.saveAndFlush(pedido);
	}

	@Override
	public Pedido statusEntregue(Long idPedido, Long idEntregador) {
		Pedido pedido = findById(idPedido);
		if (pedido == null || pedido.getEntregador() == null ||
				!pedido.getEntregador().getId().equals(idEntregador)) {
			LOG.info("Erro ao tentar setar o status entregue ao pedido de id " + idPedido +
					", devido o cancelamento do entregador de id " + idEntregador + ".");
			return null;
		}
		pedido.setStatus("entregue");
		LOG.info("Setando o status entregue ao pedido de id " + idPedido +
				", devido o cancelamento do entregador de id " + idEntregador + ".");
		return dao.saveAndFlush(pedido);
	}

	@Override
	public List<PedidoDTO> findAllStatus(String status) {
		List<Pedido> listaPedidos = dao.findByStatusOrderByNomeRestaurante(status);
		List<PedidoDTO> listaPedidosDTO = new ArrayList<>();
		for (Pedido pedido : listaPedidos){
			if ( pedido.getCliente() != null)
				listaPedidosDTO.add(new PedidoDTO(pedido));
		}
		LOG.info("Listando todos pedidos com o status "+ status + ".");
		return listaPedidosDTO;
	}

	@Override
	public List<PedidoDTO> findAllEntregadorStatus(Long id, String status) {
		List<Pedido> listaPedidos = dao.findByEntregadorIdAndStatus(id, status);
		List<PedidoDTO> listaPedidosDTO = new ArrayList<>();
		for (Pedido pedido : listaPedidos){
			if ( pedido.getCliente() != null)
				listaPedidosDTO.add(new PedidoDTO(pedido));
		}
		LOG.info("Listando todos pedidos com o status "+ status + ", do entregador (id "+ id + ").");
		return listaPedidosDTO;
	}
}
