package com.obsei.portal.areas_pesquisa;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.obsei.portal.grupo_pesquisa.GrupoPesquisa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Table(name = "area_pesquisa")
@JsonSerialize(using=AreaPesquisaSerializable.class)
public class AreaPesquisa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;
}
