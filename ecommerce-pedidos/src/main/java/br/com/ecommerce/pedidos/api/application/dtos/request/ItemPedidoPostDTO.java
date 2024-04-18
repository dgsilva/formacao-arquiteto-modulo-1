package br.com.ecommerce.pedidos.api.application.dtos.request;

import lombok.Data;

@Data
public class ItemPedidoPostDTO {

	private Integer idProduto;
	private Integer quantidade;
}
