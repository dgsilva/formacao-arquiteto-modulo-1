package br.com.ecommerce.pedidos.api.application.dtos.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PedidosGetDTO {
	
	private String numeroPedido;
	private Date dataHoraPedido;
	private String tipoPagamento;
	private Integer qtdParcelas;
	private BigDecimal totalPedido;
	private String status;
	
	private ClienteGetDTO cliente;
	private List<ItemPedidoGetDTO> itensPedido;
}
