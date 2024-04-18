package br.com.ecommerce.pedidos.api.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ecommerce.pedidos.api.domain.interfaces.IAuthService;
import br.com.ecommerce.pedidos.api.domain.models.AuthRequestModel;
import br.com.ecommerce.pedidos.api.domain.models.AuthResponseModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService  {

	@Value("${api.pagamentos.uri}")
	private String uri;

	@Override
	public AuthResponseModel create(AuthRequestModel model) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		AuthResponseModel response = restTemplate.postForEntity(uri + "/api/login", model, AuthResponseModel.class).getBody();
		System.out.println(response);
		return response;
	}

	
}
