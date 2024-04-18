package br.com.ecommerce.cliente.api.dtos.response;

import br.com.ecommerce.cliente.api.models.Cliente;
import lombok.Data;

@Data
public class AuthenticationResponseDTO {

	private String message;
	private String accessToken;
	private Cliente data;
}
