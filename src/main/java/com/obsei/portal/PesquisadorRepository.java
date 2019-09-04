package com.obsei.portal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesquisadorRepository extends CrudRepository<Pesquisador, Long> {}
