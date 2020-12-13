package com.redworks.clocker.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.redworks.clocker.persistence.entities.User;
import com.redworks.clocker.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.redworks.clocker.security.Constants.*;

@Configurable
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private UserService userService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		// This Class is an image of the JSON object contained in
		// request Body. Not more.
		User credentials = null;
		try {
			// Try to read the body to get the Username / Password
			credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);
		} catch (IOException e) {
			credentials = null;
		}

		if (credentials != null && credentials.getUsername() != null && credentials.getPassword() != null) {

			// The Username & Password are passed to authenticate
			// method. This one will verify the compliance
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
					credentials.getUsername().toLowerCase(), credentials.getPassword(),
					new ArrayList<>()));
		} else {
			// In any error case, return something that will be invalid
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken("none", "none", new ArrayList<>()));
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String login = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		Claims claims = Jwts.claims().setSubject(login);

		if (login != null && login.length() > 0) {
			UserDetails user = userService.loadUserByUsername(login);
			claims.put(AUTHORITIES_KEY, AuthorityUtils.authorityListToSet(user.getAuthorities()));
		}
		
		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO).setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();

		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + token);
		response.getWriter().write("{\"Authorization\": \"" + TOKEN_BEARER_PREFIX + token + "\"}");
	}

}
