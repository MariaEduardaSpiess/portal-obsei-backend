package com.obsei.portal.grupo_pesquisa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.obsei.portal.areas_pesquisa.AreaPesquisa;
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
@Table(name = "grupo_pesquisa")
public class GrupoPesquisa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "area_pesquisa_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private AreaPesquisa areaPesquisa;
}
