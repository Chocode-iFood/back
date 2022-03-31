package br.com.chocode.back.services;

import br.com.chocode.back.DTO.PedidoDTO;
import br.com.chocode.back.dao.PedidoDAO;
import br.com.chocode.back.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoServiceImpl implements IPedidoService {
	private PedidoDAO dao;
	private IEntregadorService entregadorService;
	private IClienteService clienteService;

	@Autowired
	public PedidoServiceImpl(PedidoDAO dao, IEntregadorService entregadorService, IClienteService clienteService) {
		this.dao = dao;
		this.entregadorService = entregadorService;
		this.clienteService = clienteService;
	}


	public Pedido save(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido(pedidoDTO, clienteService.findById(pedidoDTO.getIdCliente()));
		return dao.saveAndFlush(pedido);
	}

	public List<Pedido> findAll() {
		List<Pedido> listaPedidos = dao.findAll(Sort.by(Sort.Direction.ASC, "nomeRestaurante"));
		return listaPedidos;
	}

	public Pedido findById(Long id) {
		Pedido pedido = dao.findById(id).get();
		return pedido;
	}

	public Pedido saveEntregador(Long idPedido, Long idEntregador) {
		Pedido pedido = findById(idPedido);
		pedido.setEntregador(entregadorService.findById(idEntregador));
		pedido.setStatus("a_caminho");
		return dao.saveAndFlush(pedido);
	}

	public Pedido statusCancelado(Long idPedido, Long idEntregador) {
		Pedido pedido = findById(idPedido);
		if (pedido == null || pedido.getEntregador() == null || !pedido.getEntregador().getId().equals(idEntregador))
			return null;
		pedido.setEntregador(null);
		pedido.setStatus("cancelado");
		return dao.saveAndFlush(pedido);
	}

	public Pedido statusEntregue(Long idPedido, Long idEntregador) {
		Pedido pedido = findById(idPedido);
		if (pedido == null || pedido.getEntregador() == null || !pedido.getEntregador().getId().equals(idEntregador))
			return null;
		pedido.setStatus("entregue");
		return dao.saveAndFlush(pedido);
	}

	public List<PedidoDTO> findAllStatus(String status) {
		List<Pedido> listaPedidos = dao.findByStatus(status);
		List<PedidoDTO> listaPedidosDTO = new ArrayList<>();
		for (Pedido pedido : listaPedidos){
			if ( pedido.getCliente() != null)
				listaPedidosDTO.add(new PedidoDTO(pedido));
		}
		return listaPedidosDTO;
	}

}
