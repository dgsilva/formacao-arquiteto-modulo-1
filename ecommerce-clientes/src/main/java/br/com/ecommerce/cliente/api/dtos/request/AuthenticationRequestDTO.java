package br.com.ecommerce.cliente.api.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationRequestDTO {

	@Email(message = "Email do cliente é inválido.")
	@NotBlank(message = "Email do cliente é obrigatorio.")
	private String email;
	
	@Size(min = 8, max = 20, message = "Senha do cliente é obrigatória.")
	@NotBlank(message = "Senha do cliente é obrigatória.")
	private String senha;
	
}
