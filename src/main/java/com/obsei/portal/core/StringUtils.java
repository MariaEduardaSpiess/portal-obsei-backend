package com.obsei.portal.core;

public class StringUtils {

	public static String padR(String texto, int tamanho, String preenchimento) {
		String ret=texto;
		
		for (int i=0;i<texto.length()+tamanho;i++) {
			ret = ret + preenchimento;
		}
		
		return ret.substring(0, tamanho);
	}
	
}
