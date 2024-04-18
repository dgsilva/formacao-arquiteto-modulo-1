package br.com.ecommerce.pedidos.api.dto;

import java.math.BigDecimal;

import br.com.ecommerce.pedidos.api.model.Auth;
import lombok.Data;

@Data
public class CheckoutRequestModel {
	private Long idCheckout;
	private String number;
	private String holderName;
	private String holderDocument;
	private Integer expirationMonth;
	private Integer expirationYear;
	private Integer cvv;
	private BigDecimal amount;
	private String dueAt;
	private Integer installments;
	private Auth clientId;
}
