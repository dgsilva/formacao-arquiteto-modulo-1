package br.com.ecommerce.cliente.api.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequestDTO {

	@Size(min = 6 , max = 150, message = "Nome do cliente deve ser de 6 a 150 caracteres.")
	@NotBlank(message = "Nome do cliente é obrigatório.")
	private String nome;
	
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Telefone deve ter 11 digitos numéricos.")
	@NotBlank(message = "Telefone do cliente é obrigatório.")
	private String telefone;
	
	@Email(message = "Email do cliente é inválido.")
	@NotBlank(message = "Email do cliente é obrigatorio.")
	private String email;
	
	@Size(min = 8, max = 20, message = "Senha do cliente é obrigatória.")
	@NotBlank(message = "Senha do cliente é obrigatória.")
	private String senha;
}
