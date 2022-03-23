package br.com.chocode.ifood.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {
	
//	@Autowired
//	private ILoginService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody String email,String senha){
		System.out.printf("Front email -> ", email);
		System.out.printf("Front senha -> ", senha);
		return ResponseEntity.status(200).body("Mete Marcha");
	}
	
	

}
