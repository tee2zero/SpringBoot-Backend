package com.bedtaletshop.springbootbackend.security;

import static com.bedtaletshop.springbootbackend.security.SecurityConstants.CLAIMS_ROLE;
import static com.bedtaletshop.springbootbackend.security.SecurityConstants.EXPIRATION_TIME;
import static com.bedtaletshop.springbootbackend.security.SecurityConstants.SECRET_KEY;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bedtaletshop.springbootbackend.model.User;
import com.bedtaletshop.springbootbackend.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bedtaletshop.springbootbackend.controller.request.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFiller extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final UserService userService;

	public JWTAuthenticationFiller(AuthenticationManager authenticationManager,UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {

			UserRequest userRequest = new ObjectMapper().readValue(request.getInputStream(), UserRequest.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
					userRequest.getPassword(), new ArrayList<>()));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		if (authResult.getPrincipal() != null) {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult
					.getPrincipal();

			// JWT
			String username = user.getUsername();
			if (username != null && username.length() > 0) {
				User userInDB = userService.findUserByUsername(username);
				Claims claims = Jwts.claims().setSubject(username).setIssuer("teebee").setAudience("www.teebee.com");

				List<String> roles = new ArrayList<>();
				user.getAuthorities().stream().forEach(atuthority -> roles.add(atuthority.getAuthority()));
				claims.put(CLAIMS_ROLE, roles);
				claims.put("value", "foo");

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");

				Map<String, Object> responseJSON = new HashMap<>();
				responseJSON.put("token", createToken(claims));
				responseJSON.put("userName",userInDB.getUsername());
				responseJSON.put("userId",userInDB.getId());

				OutputStream out = response.getOutputStream();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writerWithDefaultPrettyPrinter().writeValue(out, responseJSON);
				out.flush();

			}
		}
	}

	private String createToken(Claims claims) {
		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
}
