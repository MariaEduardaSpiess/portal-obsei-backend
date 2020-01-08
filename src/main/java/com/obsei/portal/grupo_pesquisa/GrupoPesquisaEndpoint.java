package com.obsei.portal.grupo_pesquisa;

import com.obsei.portal.areas_pesquisa.AreaPesquisaRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.obsei.portal.exception.ResourceNotFoundException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class GrupoPesquisaEndpoint {
	
	@Resource
	private EntityManager em;
	
	private GrupoPesquisaRepository repository;

	@Autowired
	private AreaPesquisaRepository areaPesquisaRepository;

	public GrupoPesquisaEndpoint(GrupoPesquisaRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/grupos-pesquisa")
	public ResponseEntity<List<GrupoPesquisa>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}
	
	@PostMapping(path = "/grupo-pesquisa")
	public ResponseEntity<GrupoPesquisa> cadastrar(@Valid @RequestBody GrupoPesquisa grupoPesquisa) {
		return ResponseEntity.ok(repository.save(grupoPesquisa));
	}
	
	@PutMapping(path = "/grupo-pesquisa")
	public ResponseEntity<GrupoPesquisa> atualizar(@Valid @RequestBody GrupoPesquisa grupoPesquisa) {
		return ResponseEntity.ok(repository.save(grupoPesquisa));
	}
}
