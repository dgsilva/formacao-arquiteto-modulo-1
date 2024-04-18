package br.com.ecommerce.pedidos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.pedidos.api.dto.AuthRequestDTO;
import br.com.ecommerce.pedidos.api.dto.AuthRequestPostDTO;
import br.com.ecommerce.pedidos.api.dto.AuthResponseModel;
import br.com.ecommerce.pedidos.api.model.Auth;
import br.com.ecommerce.pedidos.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/login")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Operation(summary = "Cadastrar o usuario")
	@PostMapping("/create")
	public Auth create(@RequestBody AuthRequestPostDTO dto){
		return authService.create(dto);
	}
	
	@Operation
	@PostMapping
	public ResponseEntity<AuthResponseModel>logar(@RequestBody AuthRequestDTO dto){
		return authService.acessar(dto);
	}
	
	
}
