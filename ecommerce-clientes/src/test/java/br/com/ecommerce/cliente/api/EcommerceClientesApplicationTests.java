package br.com.ecommerce.cliente.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.ecommerce.cliente.api.dtos.request.ClienteRequestDTO;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceClientesApplicationTests {

	@Autowired
	private MockMvc mockMVC;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Test
	public void postCliente() throws Exception {
		ClienteRequestDTO dto = new ClienteRequestDTO();
		Faker faker = new Faker(new Locale("pt-BR"));
		
		dto.setNome(faker.name().fullName());
		dto.setEmail(faker.internet().emailAddress());
		dto.setTelefone(faker.number().digits(11));
		dto.setSenha(faker.internet().password(8,11));
		
		mockMVC.perform(
				post("/v1/cliente/create")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated());
	
	}

}
