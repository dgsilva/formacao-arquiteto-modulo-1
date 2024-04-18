package br.com.ecommerce.cliente.api.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerce.cliente.api.dtos.request.AuthenticationRequestDTO;
import br.com.ecommerce.cliente.api.dtos.request.ClienteRequestDTO;
import br.com.ecommerce.cliente.api.dtos.request.EmailMessageDto;
import br.com.ecommerce.cliente.api.dtos.response.AuthenticationResponseDTO;
import br.com.ecommerce.cliente.api.dtos.response.ClienteResponseDTO;
import br.com.ecommerce.cliente.api.helpers.ClienteEmailHelper;
import br.com.ecommerce.cliente.api.models.Cliente;
import br.com.ecommerce.cliente.api.producers.EmailMessageProducer;
import br.com.ecommerce.cliente.api.repositories.ClienteRepository;
import br.com.ecommerce.cliente.api.security.TokenAuthenticationService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	@Autowired
	private EmailMessageProducer emailMessageProducer;
	@Autowired
	private ObjectMapper objectMapper;

	public ResponseEntity<ClienteResponseDTO> save(ClienteRequestDTO dto) throws JsonProcessingException {
		ClienteResponseDTO response = new ClienteResponseDTO();
		try {
			Cliente cliente = modelMapper.map(dto, Cliente.class);

			if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
				throw new IllegalArgumentException("Email já cadastrado, tente outro.");
			}

			if (clienteRepository.findByTelefone(cliente.getTelefone()).isPresent()) {
				throw new IllegalArgumentException("Telefone já cadastrado, tente outro.");
			}

			cliente.setSenha(getHashMd5(cliente.getSenha()));
			cliente.setCadastradoEm(Instant.now());
			cliente.setAtualizadoEm(Instant.now());
			clienteRepository.save(cliente);
			response.setMessage("Cliente cadastrado com sucesso!");
			log.info("Cliente cadastrado com sucesso!");
			response.setData(cliente);
			
			// Enviando a mensagem
			EmailMessageDto emailMessage = ClienteEmailHelper.gerarMensagemDeCriacaoDeConta(cliente);
			String message = objectMapper.writeValueAsString(emailMessage);
			emailMessageProducer.send(message);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (Exception e) {
			response.setMessage(e.getMessage());
			log.error("Erro ao cadastrar o cliente: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}
	}
	
	public Cliente get(String email, String senha) {
		Optional<Cliente> optional = clienteRepository.findByEmailAndSenha(email, getHashMd5(senha));
		
		if(optional.isEmpty()) {
			throw new IllegalArgumentException("Dados inválido, cliente não encontrado");
		}
		Cliente cliente = optional.get();
		return cliente;
		
	}
	
	public ResponseEntity<AuthenticationResponseDTO> logar(AuthenticationRequestDTO auth) {
		AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO();
		HttpStatus status = null;
		try {

			Cliente cliente = get(auth.getEmail(), auth.getSenha());

			authResponse.setMessage("Cliente obtido com sucesso.");
			log.info("Cliente obtido com sucesso.");
			authResponse.setAccessToken(tokenAuthenticationService.generateToken(cliente.getEmail(), "ROLE_CLIENTE"));
			authResponse.setData(cliente);

			status = HttpStatus.OK;

		} catch (IllegalArgumentException e) {
			authResponse.setMessage(e.getMessage());
			log.error("Erro: " + e.getMessage());
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return ResponseEntity.status(status).body(authResponse);
	}

	
	private static String getHashMd5(String value) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
		return hash.toString(16);
	}
}
