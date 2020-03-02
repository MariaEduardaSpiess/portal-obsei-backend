package com.obsei.portal.pesquisador;

import com.obsei.portal.pesquisador.foto.FotoPesquisador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PesquisadorDTO {
    private Pesquisador pesquisador;
    private FotoPesquisador fotoPesquisador;
}
