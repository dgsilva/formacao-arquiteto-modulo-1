package br.com.ecommerce.pedidos.api.dto;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class AuthResponseModel {

	private String accessToken;
	private Instant expirationDate;
	private String client_id;
	private String name;
	private Integer status;
	private String mensagem;

}