package com.obsei.portal.link_util.link;

import com.obsei.portal.areas_pesquisa.AreaPesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class LinkUtilEndpoint {
	
	@Resource
	private EntityManager em;
	
	private LinkUtilRepository repository;

	@Autowired
	private AreaPesquisaRepository areaPesquisaRepository;

	public LinkUtilEndpoint(LinkUtilRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/links-uteis")
	public ResponseEntity<List<LinkUtil>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}
	
	@PostMapping(path = "/link-util")
	public ResponseEntity<LinkUtil> cadastrar(@Valid @RequestBody LinkUtil linkUtil) {
		return ResponseEntity.ok(repository.save(linkUtil));
	}
	
	@PutMapping(path = "/link-util")
	public ResponseEntity<LinkUtil> atualizar(@Valid @RequestBody LinkUtil linkUtil) {
		return ResponseEntity.ok(repository.save(linkUtil));
	}
}
