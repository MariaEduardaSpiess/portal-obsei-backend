package com.obsei.portal.pagina_pesquisa;

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
public class PaginaPesquisaEndpoint {
	
	@Resource
	private EntityManager em;
	
	private PaginaPesquisaRepository repository;

	public PaginaPesquisaEndpoint(PaginaPesquisaRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/pagina-pesquisa")
	public ResponseEntity<List<PaginaPesquisa>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}
	
	@PostMapping(path = "/pagina-pesquisa")
	public ResponseEntity<PaginaPesquisa> cadastrar(@Valid @RequestBody PaginaPesquisa pagina) {
		return ResponseEntity.ok(repository.save(pagina));
	}
	
	@PutMapping(path = "/pagina-pesquisa")
	public ResponseEntity<PaginaPesquisa> atualizar(@Valid @RequestBody PaginaPesquisa pagina) {
		return ResponseEntity.ok(repository.save(pagina));
	}
}
