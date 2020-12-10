package com.redworks.clocker.security;

public class Constants {

	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	public static final String AUTHORITIES_KEY = "roles";
	
	public static final String ISSUER_INFO = "prueba.prueba";
	public static final String SUPER_SECRET_KEY = "1234";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
}
