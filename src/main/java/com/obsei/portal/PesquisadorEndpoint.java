package com.obsei.portal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PesquisadorEndpoint {
	
	@Resource
	private EntityManager em;
	
	private PesquisadorRepository repository;

	public PesquisadorEndpoint(PesquisadorRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/pesquisadores")
	public ResponseEntity<List<Pesquisador>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}
	
	@PostMapping(path = "/pesquisador")
	public ResponseEntity<Pesquisador> cadastrar(@Valid @RequestBody Pesquisador pesquisador) {
		return ResponseEntity.ok(repository.save(pesquisador));
	}
	
	@PutMapping(path = "/pesquisador")
	public ResponseEntity<Pesquisador> atualizar(@Valid @RequestBody Pesquisador pesquisador) {
		return ResponseEntity.ok(repository.save(pesquisador));
	}
}
