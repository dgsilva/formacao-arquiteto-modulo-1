package br.com.ecommerce.produto.api.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.produto.api.domain.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

	List<Categoria> findAllByOrderByNomeAsc();
}
