package br.com.ecommerce.produto.api.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.ecommerce.produto.api.domain.models.Categoria;

@Component
public class LoadDatabase implements ApplicationRunner {

	@Autowired
	private ICategoriaRepository categoriaRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
			categoriaRepository.save(new Categoria(1,"Informatica", null));
			categoriaRepository.save(new Categoria(2,"Eletrônica", null));
			categoriaRepository.save(new Categoria(3,"Celular", null));
			categoriaRepository.save(new Categoria(4,"Gamer", null));
			categoriaRepository.save(new Categoria(5,"Livraria", null));
			categoriaRepository.save(new Categoria(6,"Outros", null));
	}
	
}
