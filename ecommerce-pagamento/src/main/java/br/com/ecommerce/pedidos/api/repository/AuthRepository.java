package br.com.ecommerce.pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ecommerce.pedidos.api.model.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {

	Auth findByName(String name);
	
	@Query("from Auth a where  a.name = :param1 and a.key = :param2 ")
	Auth findByNameAndKey(@Param("param1") String name, @Param("param2") String key);
}
