package br.com.chocode.back.controller;

import br.com.chocode.back.model.Entregador;
import br.com.chocode.back.services.IEntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/entregador")
public class EntregadorController {
	
	@Autowired
	private IEntregadorService service;

	@PostMapping
	public ResponseEntity<Entregador> save(@RequestBody Entregador entregador) {
		return ResponseEntity.status(201).body(service.save(entregador));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Entregador>> findAll() {
		return ResponseEntity.status(200).body(service.findAll());
	}


	@GetMapping("/{id}")
	public ResponseEntity<Entregador> findById(@PathVariable Long id) {
		return ResponseEntity.status(200).body(service.findById(id));
	}
	
}