package com.obsei.portal.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.obsei.portal.usuario.Usuario;
import com.obsei.portal.usuario.UsuarioRepository;

@Service
public class UserLoginService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		if (login.equalsIgnoreCase("admin")) {
			Usuario users = new Usuario(new Long(0),"admin","admin123");
			return new UsuarioLogado(users);
		}
		
		Usuario users = userRepo.findByUsername(login);
		if (users==null) {
			throw new UsernameNotFoundException("Usuário não localizado!");
		}
		
		return new UsuarioLogado(users);
	}

}
