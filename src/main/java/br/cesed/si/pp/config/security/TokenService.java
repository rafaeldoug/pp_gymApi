package br.cesed.si.pp.config.security;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${pp.gym.jwt.expiration}")
	private String expiration;

	@Value("${pp.gym.jwt.secret}")
	private String secret;

	public String gerarToken(String username) {

		return Jwts.builder()
				.setIssuer("API do Sistema de Academia - PP")
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
				.signWith(SignatureAlgorithm.HS256, secret.getBytes())
				.compact();
	}

	public boolean isTokenValid(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = (Date) claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
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
