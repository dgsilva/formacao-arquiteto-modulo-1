package br.com.ecommerce.pedidos.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Auth {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String client_id;
	@JsonIgnore
	private String key;
	@OneToOne (mappedBy = "clientId", cascade = CascadeType.ALL)
	private Checkout pagamento;
}
