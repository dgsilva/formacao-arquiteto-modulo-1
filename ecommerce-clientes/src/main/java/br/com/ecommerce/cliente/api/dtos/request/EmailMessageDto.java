package br.com.ecommerce.cliente.api.dtos.request;

import lombok.Data;

@Data
public class EmailMessageDto {

	private String to;
	private String subject;
	private String body;
	
}
