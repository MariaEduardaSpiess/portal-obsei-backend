package com.obsei.portal.questionario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionarioRepository extends CrudRepository<Questionario, Long> {}
