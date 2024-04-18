package br.com.ecommerce.pedidos.api.domain.interfaces;

import br.com.ecommerce.pedidos.api.domain.models.AuthRequestModel;
import br.com.ecommerce.pedidos.api.domain.models.AuthResponseModel;

public interface IAuthService {

	AuthResponseModel create(AuthRequestModel model) throws Exception;
}
