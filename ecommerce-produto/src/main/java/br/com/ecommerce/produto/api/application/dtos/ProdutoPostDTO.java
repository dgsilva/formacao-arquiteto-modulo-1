package br.com.ecommerce.produto.api.application.dtos;


import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoPostDTO {

	@Size(min=6, max=150, message = "Nome deve ter de 6 a 150 caracteres.")
	@NotBlank(message = "Nome é obrigatorio.")
	private String nome;
	
	@Min(value = 1, message = "Preço deve ser maior ou igual a 1.")
	private BigDecimal preco;
	
	@Size(min=6, max=150, message = "Descrição deve ter de 6 a 150 caracteres.")
	@NotBlank(message = "Descrição é obrigatorio.")
	private String descricao;
	
	@Min(value = 1, message = "Quantidade deve ser maior ou igual a 1.")
	private Integer quantidade;
	
	@Min(value = 1, message = "Id da categoria deve ser maior ou igual a 1.")
	private Integer categoria_id;
}
