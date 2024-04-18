package br.com.ecommerce.pedidos.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.pedidos.api.dto.CheckoutRequestModel;
import br.com.ecommerce.pedidos.api.dto.CheckoutResponseModel;
import br.com.ecommerce.pedidos.api.model.Checkout;
import br.com.ecommerce.pedidos.api.service.CheckoutService;

@RestController
@RequestMapping("/api/pagamento")
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;
	
	@PostMapping
	public CheckoutResponseModel create(@RequestBody CheckoutRequestModel dto) {
		return checkoutService.create(dto);
	}
	@GetMapping
	public List<Checkout> findAll(){
	 return checkoutService.findAll();
	}
}
