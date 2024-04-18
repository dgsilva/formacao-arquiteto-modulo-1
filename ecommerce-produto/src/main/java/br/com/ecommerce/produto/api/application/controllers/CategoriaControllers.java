package br.com.ecommerce.produto.api.application.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.produto.api.application.dtos.CategoriaGetDTO;
import br.com.ecommerce.produto.api.domain.models.Categoria;
import br.com.ecommerce.produto.api.domain.services.CategoriaDomainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/categorias")
@Tag(name = "Categoria")
public class CategoriaControllers {

	@Autowired
	private CategoriaDomainService categoriaService;
	@Autowired
	private ModelMapper modelMapper;
	
	@Operation(summary = "Serviço para consuta de categorias")
	@GetMapping
	public ResponseEntity <List<CategoriaGetDTO>>findAll(){
		List<Categoria> categorias = categoriaService.findAll();
		List<CategoriaGetDTO> dto = modelMapper.map(categorias, new TypeToken<List<CategoriaGetDTO>>() {}.getType());
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
}
