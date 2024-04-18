package br.com.ecommerce.pedidos.api.application.dtos.request;

import lombok.Data;

@Data
public class CobrancaPostDTO {

	private String numeroCartao;
	private String nomeTitular;
	private String cpfTitular;
	private Integer mesVencimento;
	private Integer anoVencimento;
	private Integer codigoSeguranca;
}
