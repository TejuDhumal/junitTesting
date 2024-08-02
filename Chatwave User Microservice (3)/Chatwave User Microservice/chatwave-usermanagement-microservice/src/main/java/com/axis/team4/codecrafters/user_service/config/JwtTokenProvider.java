// JWT Token Provider for generating, parsing and validating JWT tokens

package com.axis.team4.codecrafters.user_service.config;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	private final SecretKey key = Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes());

	public String generateJwtToken(Authentication authentication) {
		return Jwts.builder().setIssuer("Chat Wave").setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
				.claim("email", authentication.getName()).signWith(key).compact();
	}

//This method is used to get the email from the token.
	public String getEmailFromToken(String token) {
		try {
			logger.info("Before claims: token received - " + token);

			Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

			return claims.get("email", String.class);
		} catch (Exception e) {
			logger.info("Error parsing JWT token: " + e.getMessage());
			return null;
		}
	}

//This method is used to get the authorities from the token.
	public String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<String> authoritySet = new HashSet<>();
		for (GrantedAuthority authority : authorities) {
			authoritySet.add(authority.getAuthority());
		}
		return String.join(",", authoritySet);
	}

//This method is used to validate the token.
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
