package br.com.ecommerce.pedidos.api.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ecommerce.pedidos.api.domain.interfaces.ICheckoutService;
import br.com.ecommerce.pedidos.api.domain.models.CheckoutRequestModel;
import br.com.ecommerce.pedidos.api.domain.models.CheckoutResponseModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckoutService implements ICheckoutService {
	
	@Value("${api.pagamentos.uri}")
	private String uri;

	
	@Override
	public CheckoutResponseModel create(CheckoutRequestModel model, String accessToken) throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", "Bearer " + accessToken);

		HttpEntity<CheckoutRequestModel> httpEntity = new HttpEntity<CheckoutRequestModel>(model, httpHeaders);

		RestTemplate restTemplate = new RestTemplate();
		CheckoutResponseModel response = restTemplate.postForEntity(uri + "/api/pagamento", httpEntity,CheckoutResponseModel.class)
				.getBody();
		
		return response;
	}

}
