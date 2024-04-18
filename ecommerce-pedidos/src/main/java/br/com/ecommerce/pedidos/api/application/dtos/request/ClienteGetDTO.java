package br.com.ecommerce.pedidos.api.application.dtos.request;

import lombok.Data;

@Data
public class ClienteGetDTO {
	
	private Integer id;
	private String nome;
	private String telefone;
	private String email;
}
