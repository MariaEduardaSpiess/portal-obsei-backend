package com.obsei.portal.pesquisador;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "consultaPesquisadores", types = { Pesquisador.class })
public interface ConsultaPesquisadores {
}
