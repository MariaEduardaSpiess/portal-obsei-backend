package com.obsei.portal.pesquisador;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesquisadorRepository extends CrudRepository<Pesquisador, Long> {}
