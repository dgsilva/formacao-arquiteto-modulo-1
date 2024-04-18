package br.com.ecommerce.pedidos.api.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.pedidos.api.dto.AuthRequestDTO;
import br.com.ecommerce.pedidos.api.dto.AuthResponseModel;
import br.com.ecommerce.pedidos.api.dto.CheckoutDataResponseModel;
import br.com.ecommerce.pedidos.api.dto.CheckoutRequestModel;
import br.com.ecommerce.pedidos.api.dto.CheckoutResponseModel;
import br.com.ecommerce.pedidos.api.model.Auth;
import br.com.ecommerce.pedidos.api.model.Checkout;
import br.com.ecommerce.pedidos.api.repository.CheckoutRepository;

@Service
public class CheckoutService {

	@Autowired
	private CheckoutRepository checkoutRepository;
	
	public List<Checkout> findAll(){
		return checkoutRepository.findAll();
	}
	
	
	public CheckoutResponseModel create(CheckoutRequestModel dto) {
	    try {
	        // Mapeia o DTO para a entidade Checkout
	        Checkout checkout = new Checkout();
	        checkout.setNumber(dto.getNumber());
	        checkout.setHolderName(dto.getHolderName());
	        checkout.setHolderDocument(dto.getHolderDocument());
	        checkout.setExpirationMonth(dto.getExpirationMonth());
	        checkout.setExpirationYear(dto.getExpirationYear());
	        checkout.setCvv(dto.getCvv());
	        checkout.setAmount(dto.getAmount());
	        checkout.setDueAt(dto.getDueAt());
	        checkout.setInstallments(dto.getInstallments());
	        checkout.setClientId(dto.getClientId());

	        // Salva o checkout no banco de dados
	        checkout = checkoutRepository.save(checkout);


	        // Cria um objeto AuthResponseModel e atribui os valores necessários
	        AuthResponseModel authResponseModel = new AuthResponseModel();
	        authResponseModel.setClient_id(checkout.getClientId().getClient_id());


	        // Cria um objeto CheckoutDataResponseModel com os dados relevantes
	        CheckoutDataResponseModel data = new CheckoutDataResponseModel();
	        data.setAmount(checkout.getAmount());
	        data.setDueAt(Instant.now());
	        data.setInstallments(checkout.getInstallments());

	        // Verifica se o data foi preenchido corretamente

	        // Cria um objeto CheckoutResponseModel e atribui os valores necessários
	        CheckoutResponseModel response = new CheckoutResponseModel();
	        if(response != null) {
	        response.setResult("Success");
	        response.setCheckout_id(checkout.getIdCheckout());
	        response.setClientId(authResponseModel);
	        response.setData(data);
	        }else {
	        	response.setResult("Error");
	        }

	        return response;
	    } catch (Exception e) {
	        // Log de exceção
	        System.out.println("Erro ao criar checkout: " + e.getMessage());
	        
	        // Retorna uma resposta de erro
	        return createErrorResponse();
	    }
	}

	private CheckoutResponseModel createErrorResponse() {
	    CheckoutResponseModel response = new CheckoutResponseModel();
	    response.setResult("Error");
	    return response;
	}


}
	
