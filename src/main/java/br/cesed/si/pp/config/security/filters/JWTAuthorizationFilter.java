package br.cesed.si.pp.config.security.filters;

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

import br.cesed.si.pp.config.security.AutenticacaoService;
import br.cesed.si.pp.config.security.TokenService;
import br.cesed.si.pp.config.security.UsuarioDetalhesService;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private static final int INIT_TOKEN = 7;

	private TokenService tokenService;

	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, TokenService tokenService,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.tokenService = tokenService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		String token = recuperarToken(req);
		boolean valido = tokenService.isTokenValido(token);
		if (valido) {
			autenticarCliente(token);
		}
//		if (header != null && header.startsWith("Bearer ")) {
//			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(INIT_TOKEN));
//			if (auth != null) {
//				SecurityContextHolder.getContext().setAuthentication(auth);
//			}
//		}
		chain.doFilter(req, res);
	}

	private void autenticarCliente(String token) {
		 String nomeUser = tokenService.getUsername(token);
//		 AutenticacaoService user = (AutenticacaoService) userDetailsService.loadUserByUsername(nomeUser);
		 UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String recuperarToken(HttpServletRequest req) {
		String token = req.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(INIT_TOKEN, token.length());
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (tokenService.isTokenValido(token)) {
			String username = tokenService.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			System.out.println("getAuth " + username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}

}
