package br.cesed.si.pp.config.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
	
	public static AutenticacaoService authenticated() {
		try {
			return (AutenticacaoService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
