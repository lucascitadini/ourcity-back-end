package com.citadini.ourcity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
		return Jwts.builder()
				.subject(username)
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key) // New method
				.compact();
	}

	public boolean tokenValidation(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String userName = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
            return userName != null && expirationDate != null && now.before(expirationDate);
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {
			SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

			return Jwts.parser()
					.verifyWith(key)  // Use the SecretKey object
					.build()
					.parseSignedClaims(token)
					.getPayload();
		} catch (Exception e) {
			return null;
		}
	}

	public String getUserName(String token) {
		Claims claims = getClaims(token);
		if (claims != null) 
			return claims.getSubject();
		return null;
	}
	
}
