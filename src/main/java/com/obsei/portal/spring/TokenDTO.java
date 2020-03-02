package com.obsei.portal.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class TokenDTO {
	
	private String username;
	private String token;
	
}