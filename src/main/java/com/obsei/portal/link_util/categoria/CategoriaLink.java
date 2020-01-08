package com.obsei.portal.link_util.categoria;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.obsei.portal.areas_pesquisa.AreaPesquisaSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Table(name = "categoria_link")
@JsonSerialize(using=CategoriaLinkSerializable.class)
public class CategoriaLink {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;
}
