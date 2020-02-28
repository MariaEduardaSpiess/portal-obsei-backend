package com.obsei.portal.pesquisador.foto;

import com.obsei.portal.pesquisador.Pesquisador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "foto_pesquisador")
public class FotoPesquisador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public static final String FOTO="foto";
    @Lob
    @Column(name = "foto", nullable = false)
    private String base64;

    public static final String DESC_FOTO="desc_foto";
    @Column(name = "desc_foto", nullable = false)
    private String descricaoFoto;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pesquisador_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pesquisador pesquisador;
}
