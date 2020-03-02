package com.obsei.portal.questionario;

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
public class QuestionarioEndpoint {
	
	@Resource
	private EntityManager em;
	
	private QuestionarioRepository repository;

	public QuestionarioEndpoint(QuestionarioRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/api/private/questionarios")
	public ResponseEntity<List<Questionario>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(),false).collect(Collectors.toList()));
	}

	@PostMapping(path = "/api/private/questionario")
	public ResponseEntity<Questionario> cadastrar(@Valid @RequestBody Questionario questionario) {
		return ResponseEntity.ok(repository.save(questionario));
	}

	@PutMapping(path = "/api/private/questionario")
	public ResponseEntity<Questionario> atualizar(@Valid @RequestBody Questionario questionario) {
		return ResponseEntity.ok(repository.save(questionario));
	}
}
