package br.com.ecommerce.produto.api.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.produto.api.domain.models.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Integer> {

	List<Produto> findAllByCategoriaIdOrderByNomeAsc(Integer id);
}
