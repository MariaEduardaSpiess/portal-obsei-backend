package com.obsei.portal.areas_pesquisa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class AreaPesquisaEndpoint {
	
	@Resource
	private EntityManager em;
	
	private AreaPesquisaRepository repository;

	public AreaPesquisaEndpoint(AreaPesquisaRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/areas-pesquisa")
	public ResponseEntity<List<AreaPesquisa>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}

	@PostMapping(path = "/area-pesquisa")
	public ResponseEntity<AreaPesquisa> cadastrar(@Valid @RequestBody AreaPesquisa areaPesquisa) {
		return ResponseEntity.ok(repository.save(areaPesquisa));
	}

	@PutMapping(path = "/area-pesquisa")
	public ResponseEntity<AreaPesquisa> atualizar(@Valid @RequestBody AreaPesquisa areaPesquisa) {
		return ResponseEntity.ok(repository.save(areaPesquisa));
	}
}
