package br.com.chocode.back.services;

import br.com.chocode.back.DTO.PedidoDTO;
import br.com.chocode.back.model.Pedido;

import java.util.List;

public interface IPedidoService {

	public Pedido save(PedidoDTO pedidoDTO);

	public List<Pedido> findAll();

	public Pedido findById(Long id);

	public Pedido saveEntregador(Long idPedido, Long idEntregador);

	public Pedido statusCancelado(Long idPedido, Long idEntregador);

	public Pedido statusEntregue(Long idPedido, Long idEntregador);

	public List<PedidoDTO> findAllStatus(String status);

}
