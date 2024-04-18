package br.com.ecommerce.produto.api.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.produto.api.domain.interfaces.IProdutoDomainService;
import br.com.ecommerce.produto.api.domain.models.Categoria;
import br.com.ecommerce.produto.api.domain.models.Produto;
import br.com.ecommerce.produto.api.infrastructure.repositories.ICategoriaRepository;
import br.com.ecommerce.produto.api.infrastructure.repositories.IProdutoRepository;

@Service
public class ProdutoDomainService implements IProdutoDomainService {

	@Autowired
	private IProdutoRepository produtoRepository;
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	@Override
	public Produto save(Produto produto, Integer categoriaId) {
		Optional<Categoria> optional = categoriaRepository.findById(categoriaId);
		
		if(optional.isEmpty()) {
			throw new IllegalArgumentException("Categoria inválida");
		}
		
		produto.setCategoria(optional.get());
		produtoRepository.save(produto);
		
		return produto;
	}

	@Override
	public Produto delete(Integer id) {
		Optional<Produto> produtoExist = produtoRepository.findById(id);
		
		if(produtoExist.isEmpty()) {
			throw new IllegalArgumentException("Produto inválida");
		}
		
		Produto produto = produtoExist.get();
		produtoRepository.delete(produto);
		return produto;
	}

	@Override
	public List<Produto> findAll(Integer categoriaId) {
		return produtoRepository.findAllByCategoriaIdOrderByNomeAsc(categoriaId);
	}

}
