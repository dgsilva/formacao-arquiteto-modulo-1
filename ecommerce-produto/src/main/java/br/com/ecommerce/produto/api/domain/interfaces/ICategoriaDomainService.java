package br.com.ecommerce.produto.api.domain.interfaces;

import java.util.List;

import br.com.ecommerce.produto.api.domain.models.Categoria;

public interface ICategoriaDomainService {

	List<Categoria>findAll();
}
