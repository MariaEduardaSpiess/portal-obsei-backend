package com.obsei.portal.artigo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtigoRepository extends CrudRepository<Artigo, Long> {}
