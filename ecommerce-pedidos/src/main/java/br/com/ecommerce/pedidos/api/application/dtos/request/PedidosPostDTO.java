package br.com.ecommerce.pedidos.api.application.dtos.request;

import java.util.List;

import lombok.Data;

@Data
public class PedidosPostDTO {

	private List<ItemPedidoPostDTO> itensPedido;
	private String tipoPagamento;
	private Integer qtdParcelas;
	
	private CobrancaPostDTO cobranca;
}
