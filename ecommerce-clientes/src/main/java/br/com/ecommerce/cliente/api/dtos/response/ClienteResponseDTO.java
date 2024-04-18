package br.com.ecommerce.cliente.api.dtos.response;

import br.com.ecommerce.cliente.api.models.Cliente;
import lombok.Data;

@Data
public class ClienteResponseDTO {

	private String message;
	private Cliente data;
}
