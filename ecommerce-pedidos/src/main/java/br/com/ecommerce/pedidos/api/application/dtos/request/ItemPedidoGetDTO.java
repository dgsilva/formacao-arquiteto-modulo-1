package br.com.ecommerce.pedidos.api.application.dtos.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemPedidoGetDTO {

	private Integer id;
	private String nome;
	private BigDecimal preco;
	private BigDecimal quantidade;
	private String descricao;
	private BigDecimal total;
}
