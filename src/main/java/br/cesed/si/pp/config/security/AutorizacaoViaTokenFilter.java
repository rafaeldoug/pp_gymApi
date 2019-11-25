package br.cesed.si.pp.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;

public class AutorizacaoViaTokenFilter extends BasicAuthenticationFilter {

	private TokenService tokenService;

	private UserDetailsService userDetailsService;
	
	private AutenticacaoDetalhesService authService;
	
	public AutorizacaoViaTokenFilter(AuthenticationManager authenticationManager, TokenService tokenService,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.tokenService = tokenService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		String token = recuperarToken(req);
		boolean valido = tokenService.isTokenValid(token);
		System.out.println(valido);
		if (valido) {
			authenticated();
//			autenticarCliente(token);
		}
//		if(header != null && header.startsWith("Bearer ")) {
//			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
//			if(auth != null) {
//				SecurityContextHolder.getContext().setAuthentication(auth);
//			}
//		}
		chain.doFilter(req, res);
	}
	
	private void autenticarCliente(String token) {
		getAuthentication(token);
	}

	private String recuperarToken(HttpServletRequest req) {
		String token = req.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		return token.substring(7, token.length());
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		
		if(tokenService.isTokenValid(token)) {
			String username = tokenService.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
	
//	private void authenticated(String token) {
//		String nomeUser = tokenService.getUsername(token);
//		AutenticacaoService usuario =  (AutenticacaoService) authDetailsService.loadUserByUsername(nomeUser);
//		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
//		SecurityContextHolder.getContext().setAuthentication(auth);
//	}
	public static AutenticacaoService authenticated() {
		try {
			return (AutenticacaoService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}