package br.com.chocode.back.controller;

import br.com.chocode.back.DTO.PedidoDTO;
import br.com.chocode.back.model.Geolocalizacao;
import br.com.chocode.back.model.Pedido;
import br.com.chocode.back.services.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/pedido")
public class PedidoController {

	@Autowired
	private IPedidoService service;


	@PostMapping
	public ResponseEntity<Pedido> save(@RequestBody PedidoDTO pedidoDTO) {
		return ResponseEntity.status(201).body(service.save(pedidoDTO));
	}


	@PutMapping("/{idPedido}/entregador/{idEntregador}")
	public ResponseEntity<Pedido> saveEntregador(@PathVariable Long idPedido, @PathVariable Long idEntregador) {
		return ResponseEntity.status(201).body(service.saveEntregador(idPedido, idEntregador));
	}

	@PutMapping("/{idPedido}/status")
	public ResponseEntity<Pedido> saveStatus(@PathVariable Long idPedido, @RequestBody String status) {
		return ResponseEntity.status(201).body(service.saveStatus(idPedido, status));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Pedido>> findAll() {
		return ResponseEntity.status(200).body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		return ResponseEntity.status(200).body(service.findById(id));
	}




}