package com.obsei.portal.pesquisador;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "pesquisador")
public class Pesquisador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public static final String NOME="nome";
	@Column(name = "nome", nullable = false)
	private String nome;

	public static final String FUNCAO="funcao";
	@Column(name = "funcao", nullable = false)
	private String funcao;

	public static final String LATTES="lattes";
	@Column(name = "lattes", nullable = false)
	private String lattes;

	public static final String FOTO="foto";
	@Lob
	@Column(name = "foto", nullable = true)
	private String foto;
}