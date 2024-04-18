package br.com.ecommerce.pedidos.api.dto;

import java.util.UUID;

import br.com.ecommerce.pedidos.api.model.Auth;
import lombok.Data;

@Data
public class CheckoutResponseModel {

	private String result;
	private Long checkout_id;
	private AuthResponseModel clientId;
	private CheckoutDataResponseModel data;
}
