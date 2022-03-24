package br.com.chocode.ifood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.chocode.ifood.dto.EntregadorDTO;
import br.com.chocode.ifood.security.Token;
import br.com.chocode.ifood.services.IEntregadorService;

@RestController
@CrossOrigin("*")
public class LoginController {
	
//	@Autowired
//	private ILoginService service;
	
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody String email,String senha){
//		System.out.printf("Front email -> ", email);
//		System.out.printf("Front senha -> ", senha);
//		return ResponseEntity.status(200).body("Mete Marcha");
//	}
	
//	@Autowired
//	private IEntregadorService service;
//
//	@PostMapping("/login")
//	public ResponseEntity<Token> realizarLogin(@RequestBody EntregadorDTO dadosLogin) {
//		Token token = service.gerarTokenDeUsuarioLogado(dadosLogin);
//		if (token != null) {
//			return ResponseEntity.ok(token);
//		}
//		return ResponseEntity.status(401).build();
//	}
	
	

}
