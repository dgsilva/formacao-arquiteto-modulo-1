package br.com.ecommerce.produto.api.application.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoGetDTO {

	private Integer id;
	private String nome;
	private BigDecimal preco;
	private Integer quantidade;
	private String descricao;
	private CategoriaGetDTO categoria;
}
