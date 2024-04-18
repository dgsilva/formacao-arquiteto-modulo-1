package br.com.ecommerce.pedidos.api.domain.models;

import lombok.Data;

@Data
public class CheckoutResponseModel {

	private String result;
	private Long checkout_id;
	private String clientId;
	private CheckoutDataResponseModel data;

}
