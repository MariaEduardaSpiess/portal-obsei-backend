package com.obsei.portal.areas_pesquisa;

import org.springframework.http.HttpStatus;
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

	@GetMapping(path = "/api/private/areas-pesquisa")
	public ResponseEntity<List<AreaPesquisa>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}

	@PostMapping(path = "/api/private/area-pesquisa")
	public ResponseEntity<AreaPesquisa> cadastrar(@Valid @RequestBody AreaPesquisa areaPesquisa) {
		return ResponseEntity.ok(repository.save(areaPesquisa));
	}

	@PutMapping(path = "/api/private/area-pesquisa/{id}")
	public ResponseEntity<AreaPesquisa> atualizar(@PathVariable Long id, @Valid @RequestBody AreaPesquisa areaPesquisa) {
		return repository.findById(id)
				.map(record -> {
					record.setNome(areaPesquisa.getNome());
					AreaPesquisa updated = repository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/api/private/area-pesquisa/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
