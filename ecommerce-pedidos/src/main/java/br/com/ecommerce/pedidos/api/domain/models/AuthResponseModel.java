package br.com.ecommerce.pedidos.api.domain.models;

import java.time.Instant;
import java.util.Date;

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
