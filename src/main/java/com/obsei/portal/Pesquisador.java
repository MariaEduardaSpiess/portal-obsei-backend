package com.obsei.portal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

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
	
	public static final String LATTES="lattes";
	@Column(name = "latter", nullable = false)
	private String lattes;
	
	public Pesquisador() {}
	
	public Pesquisador(String nome, String lattes) {
		this.nome = nome;
		this.lattes = lattes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLattes() {
		return lattes;
	}

	public void setLattes(String lattes) {
		this.lattes = lattes;
	}
}