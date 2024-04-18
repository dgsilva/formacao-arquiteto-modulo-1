package br.com.ecommerce.cliente.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.ecommerce.cliente.api.dtos.request.ClienteRequestDTO;
import br.com.ecommerce.cliente.api.dtos.response.ClienteResponseDTO;
import br.com.ecommerce.cliente.api.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/v1/cliente")
@Tag(name = "Cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@PostMapping("/create")
	@Operation(summary = "Realizar cadastro do cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cadastrado"),
			@ApiResponse(responseCode = "400", description = "Solicitação inválida"),
	})
	public ResponseEntity<ClienteResponseDTO> post(@Valid @RequestBody ClienteRequestDTO dto) throws JsonProcessingException {
		return clienteService.save(dto);
	}
}
