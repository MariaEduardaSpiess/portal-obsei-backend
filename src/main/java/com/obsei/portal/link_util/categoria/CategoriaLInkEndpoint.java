package com.obsei.portal.link_util.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class CategoriaLInkEndpoint {
	
	@Resource
	private EntityManager em;
	
	private CategoriaLinkRepository repository;

	public CategoriaLInkEndpoint(CategoriaLinkRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/api/private/categorias-links")
	public ResponseEntity<List<CategoriaLink>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}

	@PostMapping(path = "/api/private/categoria-links")
	public ResponseEntity<CategoriaLink> cadastrar(@Valid @RequestBody CategoriaLink categoriaLink) {
		return ResponseEntity.ok(repository.save(categoriaLink));
	}

	@PutMapping(path = "/api/private/categoria-links")
	public ResponseEntity<CategoriaLink> atualizar(@Valid @RequestBody CategoriaLink categoriaLink) {
		return ResponseEntity.ok(repository.save(categoriaLink));
	}
}
