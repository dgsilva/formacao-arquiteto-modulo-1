package br.com.ecommerce.pedidos.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.pedidos.api.model.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout,Long> {

}
