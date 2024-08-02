// Purpose: JWT token provider class to generate

package com.axis.team4.codecrafters.chat_history_service.config;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private final SecretKey key = Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes());

	public String generateJwtToken(Authentication authentication) {
		return Jwts.builder().setIssuer("Chat Wave").setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
				.claim("email", authentication.getName()).signWith(key).compact();
	}

	public String getEmailFromToken(String token) {
		try {
			System.out.println("Before claims: token received - " + token);

			Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

			return claims.get("email", String.class);
		} catch (Exception e) {
			System.err.println("Error parsing JWT token: " + e.getMessage());
			return null;
		}
	}

	public String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<String> authoritySet = new HashSet<>();
		for (GrantedAuthority authority : authorities) {
			authoritySet.add(authority.getAuthority());
		}
		return String.join(",", authoritySet);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
