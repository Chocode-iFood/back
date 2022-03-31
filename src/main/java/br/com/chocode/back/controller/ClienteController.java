package br.com.chocode.back.controller;

import br.com.chocode.back.model.Cliente;
import br.com.chocode.back.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(service.save(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.status(200).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = service.findById(id);
        if (cliente == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(service.findById(id));
    }
}
