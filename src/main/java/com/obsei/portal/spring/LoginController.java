package com.obsei.portal.spring;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class LoginController {
	
	@Value("${server.host}")
	private String host;
	
	@Value("${server.port}")
	private String port;
	
	@PostMapping(value = "/api/public/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> autentica(@RequestBody LoginDTO loginDTO) {
		
		String url = String.format("%s:%s/%s", host, port, "api/login");
		
		RestTemplate restTemplate = new RestTemplate();

		RequestEntity<LoginDTO> request = RequestEntity
				.post(URI.create(url))
				.contentType(MediaType.APPLICATION_JSON)
				.body(loginDTO);
		try {
			ResponseEntity<TokenDTO> response = restTemplate.exchange(request, TokenDTO.class);
			if (response!=null && response.getHeaders()!=null && response.getHeaders().size()>0) {
				String token = null;
				if (response.getHeaders().get(TokenAuthenticationService.AUTH_HEADER_NAME)!=null && 
						response.getHeaders().get(TokenAuthenticationService.AUTH_HEADER_NAME)!=null) {
					token = response.getHeaders().get(TokenAuthenticationService.AUTH_HEADER_NAME).get(0);
				}
				return ResponseEntity.ok(new TokenDTO(loginDTO.getUsername(), token));
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}
}
