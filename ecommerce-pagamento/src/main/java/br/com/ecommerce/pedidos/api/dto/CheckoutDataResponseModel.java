package br.com.ecommerce.pedidos.api.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import lombok.Data;

@Data
public class CheckoutDataResponseModel {
	private BigDecimal amount;
	private Instant dueAt;
	private Integer installments;

}
