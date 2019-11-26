package br.cesed.si.pp.config.security;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${pp.gym.jwt.expiration}")
	private static String expiration;

	@Value("${pp.gym.jwt.secret}")
	private static String secret;

	public String gerarToken(Authentication authentication) {
		
		AutenticacaoService logado = (AutenticacaoService) authentication.getPrincipal();
		return Jwts.builder()
				.setIssuer("API Projeto PP | GYM")
				.setSubject(logado.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(2020,11,31))
				.signWith(SignatureAlgorithm.HS256, "^db@Rv?7L<z?Q;v=s5\"`hLHG,Gkh3|R;Xk![{R?w+Yv}*CvV3#RCanJa3zaZHed")
				.compact();
	}
	

	public boolean isTokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;

	}
	

}
