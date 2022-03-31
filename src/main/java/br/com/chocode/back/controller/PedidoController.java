package br.com.chocode.back.controller;

import br.com.chocode.back.DTO.PedidoDTO;
import br.com.chocode.back.model.Pedido;
import br.com.chocode.back.services.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private IPedidoService service;

	@PostMapping
	public ResponseEntity<Pedido> save(@RequestBody PedidoDTO pedidoDTO) {
		return ResponseEntity.status(201).body(service.save(pedidoDTO));
	}

	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {
		return ResponseEntity.status(200).body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		return ResponseEntity.status(200).body(service.findById(id));
	}

	@PutMapping("/{idPedido}/entregador/{idEntregador}")
	public ResponseEntity<Pedido> saveEntregador(@PathVariable Long idPedido, @PathVariable Long idEntregador) {
		Pedido pedido = service.saveEntregador(idPedido, idEntregador);
		if (pedido == null)
			return ResponseEntity.status(404).body(null);
		return ResponseEntity.status(201).body(pedido);
	}

	@PutMapping("/{idPedido}/entregador/{idEntregador}/cancelado")
	public ResponseEntity<Pedido> statusCancelado(@PathVariable Long idPedido, @PathVariable Long idEntregador) {
		Pedido pedido = service.statusCancelado(idPedido, idEntregador);
		if (pedido == null)
			return ResponseEntity.status(404).body(null);
		return ResponseEntity.status(201).body(pedido);
	}

	@PutMapping("/{idPedido}/entregador/{idEntregador}/entregue")
	public ResponseEntity<Pedido> statusEngregue(@PathVariable Long idPedido, @PathVariable Long idEntregador) {
		Pedido pedido = service.statusEntregue(idPedido, idEntregador);
		if (pedido == null)
			return ResponseEntity.status(404).body(null);
		return ResponseEntity.status(201).body(pedido);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<PedidoDTO>> findAllStatus(@PathVariable String status) {
		return ResponseEntity.status(200).body(service.findAllStatus(status));
	}

}
