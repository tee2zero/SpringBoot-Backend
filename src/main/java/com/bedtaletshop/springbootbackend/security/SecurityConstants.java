package com.bedtaletshop.springbootbackend.security;

public interface SecurityConstants {
	String SECRET_KEY = "teeBeeDevJWT";
	// Must be start always with Bearer
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_AUTHORIZATION = "Authorization";
	String CLAIMS_ROLE = "role";
	long EXPIRATION_TIME = (60 * 60 * 1000);
}
