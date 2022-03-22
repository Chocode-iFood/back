package br.com.chocode.ifood.services;

import java.util.List;

import br.com.chocode.ifood.dto.PedidoDTO;

public interface IPedidoService {

	public List<PedidoDTO> findAll();

	public PedidoDTO update(Long id, String status);
}
