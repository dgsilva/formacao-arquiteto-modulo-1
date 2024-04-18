package br.com.ecommerce.produto.api.application.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.produto.api.application.dtos.ProdutoGetDTO;
import br.com.ecommerce.produto.api.application.dtos.ProdutoPostDTO;
import br.com.ecommerce.produto.api.domain.models.Produto;
import br.com.ecommerce.produto.api.domain.services.ProdutoDomainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produto")
public class ProdutoController {

	@Autowired
	private ProdutoDomainService produtoDomainService;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Operation(summary = "Serviço para cadastro de produto.")
	@PostMapping
	public ResponseEntity<ProdutoGetDTO> post(@Valid @RequestBody ProdutoPostDTO dto) {
		Produto produto = produtoDomainService.save(modelMapper.map(dto, Produto.class), dto.getCategoria_id());
		ProdutoGetDTO response = modelMapper.map(produto, ProdutoGetDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}
	
	
	@Operation(summary = "Serviço para atualização de produto.")
	@PutMapping
	public ResponseEntity<ProdutoGetDTO> put(@Valid @RequestBody ProdutoPostDTO dto) {
		Produto produto = produtoDomainService.save(modelMapper.map(dto, Produto.class), dto.getCategoria_id());
		ProdutoGetDTO response = modelMapper.map(produto, ProdutoGetDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}
	
	
	@Operation(summary = "Serviço para exclusão de produto.")
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoGetDTO> delete(@PathVariable Integer id) {
		Produto produto = produtoDomainService.delete(id);
		ProdutoGetDTO response = modelMapper.map(produto, ProdutoGetDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
	
	@Operation(summary = "Serviço para consulta de produto por categoria.")
	@GetMapping("/{categoriaId}")
	public ResponseEntity<List<ProdutoGetDTO>> getAll(@PathVariable Integer categoriaId) {
		List<Produto> produtos = produtoDomainService.findAll(categoriaId);
		List<ProdutoGetDTO> response = modelMapper.map(produtos, new TypeToken<List<ProdutoGetDTO>>() {}.getType());
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
}
