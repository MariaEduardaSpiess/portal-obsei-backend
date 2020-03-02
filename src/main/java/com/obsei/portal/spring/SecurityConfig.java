package com.obsei.portal.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	
	public static final String SECRETPHRASE = "obsei#$security";
	
	private UserDetailsService users;
	private TokenAuthenticationService tokenAuthenticationService;
	
	public SecurityConfig(UserDetailsService users) {
		this.users = users;
		this.tokenAuthenticationService = new TokenAuthenticationService(SECRETPHRASE, users);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.cors().disable()
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/login").permitAll()		
		.mvcMatchers("/public/**").permitAll()
		.mvcMatchers("/api/admin/**").authenticated()
		.mvcMatchers("/api/private/**").authenticated()
		.anyRequest().authenticated()
		.anyRequest().permitAll()
		.and()
		.addFilterBefore(new StatelessLoginFilter("/api/login", tokenAuthenticationService, users, 
				authenticationManager()), UsernamePasswordAuthenticationFilter.class)		
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
				UsernamePasswordAuthenticationFilter.class);
		
		
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(users);
	}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }

}
