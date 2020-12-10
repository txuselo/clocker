package com.redworks.clocker.security;

import static com.redworks.clocker.security.Constants.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(HEADER_AUTHORIZACION_KEY);
		if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	@SuppressWarnings("unchecked")
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(HEADER_AUTHORIZACION_KEY);
		if (token != null) {
			Claims claims = Jwts.parser().setSigningKey(SUPER_SECRET_KEY).parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, "")).getBody();
			String user = claims.getSubject();
			ArrayList<String> roles = (ArrayList<String>) claims.get(AUTHORITIES_KEY);
			ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
			if (roles != null) {
				for (String rol : roles) {
					SimpleGrantedAuthority autority = new SimpleGrantedAuthority(rol);
					authorities.add(autority);
				}
			}

			if (user != null) {
				response.addHeader("user", user);
				response.addHeader("roles", roles.toString());
				return new UsernamePasswordAuthenticationToken(user, null, authorities);
			}
			return null;
		}
		return null;
	}
}