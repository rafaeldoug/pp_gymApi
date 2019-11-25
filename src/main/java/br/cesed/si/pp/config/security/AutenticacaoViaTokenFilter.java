package br.cesed.si.pp.config.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.cesed.si.pp.controller.dto.DadosUsuarioDto;

public class AutenticacaoViaTokenFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager;

	private TokenService tokenService;

	public AutenticacaoViaTokenFilter(AuthenticationManager auth, TokenService tokenService) {
		this.authManager = auth;
		this.tokenService = tokenService;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			DadosUsuarioDto creds = new ObjectMapper().readValue(req.getInputStream(), DadosUsuarioDto.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),
					creds.getPassword(), new ArrayList<>());
			Authentication auth = authManager.authenticate(authToken);
			return auth;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((UsernamePasswordAuthenticationToken) auth.getPrincipal()).getName();
		String token = tokenService.gerarToken(username);
		res.addHeader("Authorization", "Bearer " + token);

	}
	
}
