package br.com.ecommerce.produto.api.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.produto.api.domain.interfaces.ICategoriaDomainService;
import br.com.ecommerce.produto.api.domain.models.Categoria;
import br.com.ecommerce.produto.api.infrastructure.repositories.ICategoriaRepository;

@Service
public class CategoriaDomainService implements ICategoriaDomainService {

	@Autowired
	private ICategoriaRepository categoriaService;

	@Override
	public List<Categoria> findAll() {
		return categoriaService.findAllByOrderByNomeAsc();
	}

}
