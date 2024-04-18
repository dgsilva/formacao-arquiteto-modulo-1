package br.com.ecommerce.produto.api.domain.interfaces;

import java.util.List;

import br.com.ecommerce.produto.api.domain.models.Produto;

public interface IProdutoDomainService {

	Produto save(Produto produto, Integer categoriaId);
	Produto delete(Integer id);
	List<Produto>findAll(Integer categoriaId);
	
	
}
