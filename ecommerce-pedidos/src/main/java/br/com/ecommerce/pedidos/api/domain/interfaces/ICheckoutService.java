package br.com.ecommerce.pedidos.api.domain.interfaces;

import br.com.ecommerce.pedidos.api.domain.models.CheckoutRequestModel;
import br.com.ecommerce.pedidos.api.domain.models.CheckoutResponseModel;

public interface ICheckoutService {

	CheckoutResponseModel create(CheckoutRequestModel model, 
			String accessToken) throws Exception;

}
