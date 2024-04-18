package br.com.ecommerce.pedidos.api.dto;

import lombok.Data;

@Data
public class AuthRequestPostDTO {

	private String name;
	private String client_id;
	private String key;
}
