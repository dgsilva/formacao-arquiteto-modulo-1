package br.com.ecommerce.pedidos.api.application.controllers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.pedidos.api.application.dtos.request.PedidosGetDTO;
import br.com.ecommerce.pedidos.api.application.dtos.request.PedidosPostDTO;
import br.com.ecommerce.pedidos.api.domain.interfaces.IAuthService;
import br.com.ecommerce.pedidos.api.domain.interfaces.ICheckoutService;
import br.com.ecommerce.pedidos.api.domain.models.AuthRequestModel;
import br.com.ecommerce.pedidos.api.domain.models.AuthResponseModel;
import br.com.ecommerce.pedidos.api.domain.models.CheckoutRequestModel;
import br.com.ecommerce.pedidos.api.domain.models.CheckoutResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/pedidos")
@Tag(name = "Pedidos")
public class PedidosController {

	
	@Autowired
	private IAuthService authService;

	@Autowired
	private ICheckoutService checkoutService;

	@Value("${api.pagamentos.user.name}")
	private String name;

	@Value("${api.pagamentos.user.key}")
	private String key;

	@PostMapping
	@Operation(summary = "Serviço para realização de pedidos")
	public ResponseEntity<PedidosGetDTO> post(@Valid @RequestBody PedidosPostDTO dto) {

		try {

			// autenticação na API de pagamentos
			AuthRequestModel authRequest = new AuthRequestModel();
			authRequest.setName(name);
			authRequest.setKey(key);

			AuthResponseModel authResponse = authService.create(authRequest);

			// processando um pedido
			CheckoutRequestModel checkoutRequest = new CheckoutRequestModel();
			checkoutRequest.setNumber(dto.getCobranca().getNumeroCartao());
			checkoutRequest.setHolderName(dto.getCobranca().getNomeTitular());
			checkoutRequest.setHolderDocument(dto.getCobranca().getCpfTitular());
			checkoutRequest.setExpirationMonth(dto.getCobranca().getMesVencimento());
			checkoutRequest.setExpirationYear(dto.getCobranca().getAnoVencimento());
			checkoutRequest.setCvv(dto.getCobranca().getCodigoSeguranca());
			checkoutRequest.setAmount(new BigDecimal(999.99));
			checkoutRequest.setDueAt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			checkoutRequest.setInstallments(dto.getQtdParcelas());

			CheckoutResponseModel checkoutResponse = checkoutService.create(checkoutRequest,
					authResponse.getAccessToken());

			return ResponseEntity.status(HttpStatus.CREATED).body(null);

		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	
	@Operation(summary = "Serviço para consulta de pedidos")
	@GetMapping
	public ResponseEntity<String> getAll(){
		return null;
	}
}
