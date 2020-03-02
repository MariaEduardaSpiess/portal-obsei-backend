package com.obsei.portal.spring;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class TokenHandler {

	private final String SEP = ":_";
	
    private final String secret;
    private final UserDetailsService userService;

    public TokenHandler(String secret, UserDetailsService userService) {
        this.secret = secret;
        this.userService = userService;
    }

    public UserDetails parseUserFromToken(String token) {    	
        String username = parseUsernameFromToken(token);
        return userService.loadUserByUsername(username);
    }
    
    public String parseUsernameFromToken(String token) {
    	
    	String decrypt = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    	
    	String[] arrToken = decrypt.split(SEP);
    	    	
    	return arrToken[0];
    }

    public String createTokenForUser(UserDetails user) {
        return Jwts.builder()
                .setSubject(String.format("%s%s%s",user.getUsername(),SEP,""))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}