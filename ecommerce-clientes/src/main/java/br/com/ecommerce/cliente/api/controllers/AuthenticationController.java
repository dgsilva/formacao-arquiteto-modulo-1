package br.com.ecommerce.cliente.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.cliente.api.dtos.request.AuthenticationRequestDTO;
import br.com.ecommerce.cliente.api.dtos.response.AuthenticationResponseDTO;
import br.com.ecommerce.cliente.api.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Autenticação")
@RequestMapping("/v1/auth")
public class AuthenticationController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	@Operation(summary = " Serviço para autenticação de clientes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Autenticação"),
			@ApiResponse(responseCode = "401", description = "Solicitação inválida"),
	})
	public ResponseEntity<AuthenticationResponseDTO> post(@Valid @RequestBody AuthenticationRequestDTO dto){
		return clienteService.logar(dto);
	}
	
}
