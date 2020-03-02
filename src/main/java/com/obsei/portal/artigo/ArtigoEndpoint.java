package com.obsei.portal.artigo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ArtigoEndpoint {
	
	@Resource
	private EntityManager em;
	
	private ArtigoRepository repository;

	public ArtigoEndpoint(ArtigoRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/api/private/artigos")
	public ResponseEntity<List<Artigo>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}

	@PostMapping(path = "/api/private/artigo")
	public ResponseEntity<Artigo> cadastrar(@Valid @RequestBody Artigo artigo) {
		return ResponseEntity.ok(repository.save(artigo));
	}

	@PutMapping(path = "/api/private/artigo")
	public ResponseEntity<Artigo> atualizar(@Valid @RequestBody Artigo artigo) {
		return ResponseEntity.ok(repository.save(artigo));
	}
}
