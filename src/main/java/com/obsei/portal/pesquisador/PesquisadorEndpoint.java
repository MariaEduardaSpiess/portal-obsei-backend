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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

@RestController
public class PesquisadorEndpoint {

    @Resource
    private EntityManager em;

    private PesquisadorRepository repository;
    @Autowired
    private FotoPesquisadorRepository fotoPesquisadorRepository;

    public PesquisadorEndpoint(PesquisadorRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/api/private/pesquisadores")
    public ResponseEntity<List<Pesquisador>> findAll() {
        return ResponseEntity.ok(StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList()));
    }

    @Transactional
    @GetMapping(path = "/api/private/foto-pesquisador/{id}")
    public ResponseEntity<FotoPesquisador> getFotoPesquisador(@PathVariable Long id) {
        Pesquisador pesquisador = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return ResponseEntity.ok(pesquisador.getFotoPesquisador());
    }

    @PostMapping(path = "/api/private/pesquisador")
    public ResponseEntity<PesquisadorDTO> cadastrar(@Valid @RequestBody PesquisadorDTO payload) {
        FotoPesquisador fotoPesquisadorInserida = fotoPesquisadorRepository.save(payload.getFotoPesquisador());
        Pesquisador pesquisador = payload.getPesquisador();
        pesquisador.setFotoPesquisador(fotoPesquisadorInserida);
        Pesquisador pesquisadorInserido = repository.save(pesquisador);
        payload.setPesquisador(pesquisadorInserido);
        payload.setFotoPesquisador(fotoPesquisadorInserida);
        return ResponseEntity.ok(payload);
    }

    @Transactional
    @PutMapping(path = "/api/private/pesquisador/{id}")
    public ResponseEntity<PesquisadorDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PesquisadorDTO payload) {
        FotoPesquisador fotoPesquisador = payload.getFotoPesquisador();
        payload.setFotoPesquisador(fotoPesquisadorRepository.findById(fotoPesquisador.getId()).map(fotoBanco -> {
			fotoBanco.setBase64(fotoPesquisador.getBase64());
			fotoBanco.setDescricaoFoto(fotoPesquisador.getDescricaoFoto());
			return fotoPesquisadorRepository.save(fotoBanco);
		}).orElseThrow(() -> new EntityNotFoundException(id.toString())));

        payload.setPesquisador(repository.findById(id).map(record -> {
			Pesquisador pesquisador = payload.getPesquisador();
			record.setNome(pesquisador.getNome());
			record.setFuncao(pesquisador.getFuncao());
			record.setLattes(pesquisador.getLattes());
			record.setFotoPesquisador(fotoPesquisador);
			return repository.save(record);
		}).orElseThrow(() -> new EntityNotFoundException(id.toString())));

        return ResponseEntity.ok(payload);
    }

    @DeleteMapping(path = "/api/private/pesquisador/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        Pesquisador pesquisador = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
//		fotoPesquisadorRepository.deleteByPesquisador(pesquisador);
        repository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
