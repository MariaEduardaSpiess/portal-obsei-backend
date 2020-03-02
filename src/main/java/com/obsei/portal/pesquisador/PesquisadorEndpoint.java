package com.obsei.portal.pesquisador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.obsei.portal.pesquisador.foto.FotoPesquisador;
import com.obsei.portal.pesquisador.foto.FotoPesquisadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

@RestController
public class PesquisadorEndpoint {
	
	@Resource
	private EntityManager em;
	
	private PesquisadorRepository repository;
	private FotoPesquisadorRepository fotoPesquisadorRepository;

	public PesquisadorEndpoint(PesquisadorRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/api/private/pesquisadores")
	public ResponseEntity<List<Pesquisador>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}

//	@GetMapping(path = "/foto-pesquisador/{id}")
//	public ResponseEntity<byte[]> getFotoPesquisador(@PathVariable Long id) {
//		Pesquisador pesquisador = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
//		return ResponseEntity
//				.ok()
//				.contentType(MediaType.IMAGE_JPEG)
//				.body(pesquisador.getFoto().getBytes());
//	}
	
	@PostMapping(path = "/pesquisador")
	public ResponseEntity<Pesquisador> cadastrar(@Valid @RequestBody Pesquisador pesquisador) {
		return ResponseEntity.ok(repository.save(pesquisador));
	}
	
	@PutMapping(path = "/pesquisador/{id}")
	public ResponseEntity<Pesquisador> atualizar(@PathVariable Long id, @Valid @RequestBody Pesquisador pesquisador) {
		return repository.findById(id)
				.map(record -> {
					record.setNome(pesquisador.getNome());
					record.setFuncao(pesquisador.getFuncao());
					record.setLattes(pesquisador.getLattes());
//					record.setFoto(pesquisador.getFoto());
//					record.setDescricaoFoto(pesquisador.getDescricaoFoto());
					Pesquisador updated = repository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/pesquisador/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
