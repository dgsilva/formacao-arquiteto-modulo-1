package br.com.ecommerce.pedidos.api.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "checkout")
public class Checkout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCheckout;
	private String number;
	private String holderName;
	private String holderDocument;
	private Integer expirationMonth;
	private Integer expirationYear;
	private Integer cvv;
	private BigDecimal amount;
	private String dueAt;
	private Integer installments;
	@ManyToOne // Define o carregamento preguiçoso (lazy loading)
    @JoinColumn(name = "auth_id")
	private Auth clientId;

}