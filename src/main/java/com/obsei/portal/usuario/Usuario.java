package com.obsei.portal.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Component
@Table(name = "usuario")
@JsonSerialize(using=UsuarioSerializable.class)
public @Data class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false, length = 200)
	private String password;
	
	public Usuario(Long id, String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	public void setPassword(String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder(10);
		this.password = encoder.encode(password);
	}
	
}
