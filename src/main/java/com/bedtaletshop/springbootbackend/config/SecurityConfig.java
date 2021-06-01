package com.bedtaletshop.springbootbackend.config;

import javax.servlet.http.HttpServletResponse;

import com.bedtaletshop.springbootbackend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bedtaletshop.springbootbackend.security.CustomUserDetailsService;
import com.bedtaletshop.springbootbackend.security.JWTAuthenticationFiller;
import com.bedtaletshop.springbootbackend.security.JWTAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService customUserDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserService userService;

	private static final String[] AUTH_WHITELIST = {
			"/auth/register",
			"/images/*",
			"/product/public/**",
			"/category/public/**",
			"/order/public/**" // for debug first when okay back to auth,
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Enable CORS and disable CSRF
		http = http.cors().and().csrf().disable();

		// Set session management to stateless
		http = http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and();

		// Set unauthorized requests exception handler
		http = http
				.exceptionHandling()
				.authenticationEntryPoint(
						(request, response, ex) -> {
							response.sendError(
									HttpServletResponse.SC_UNAUTHORIZED,
									ex.getMessage()
							);
						}
				)
				.and();

		// Set permissions on endpoints
		http.authorizeRequests()
				// Our public endpoints
				.antMatchers(AUTH_WHITELIST).permitAll()
				.antMatchers(HttpMethod.POST, "/products/add").hasAnyAuthority("admin")
				.antMatchers(HttpMethod.PUT, "/product/edit/*").hasAnyAuthority("admin")
				.antMatchers(HttpMethod.DELETE, "/product/delete/*").hasAnyAuthority("admin")
				// Our private endpoints
				.anyRequest().authenticated();

		// Add JWT token filter
		http.addFilter(authenticationFilter()).sessionManagement().and().addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement();

	}

	@Bean
	UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
		final UsernamePasswordAuthenticationFilter filler = new JWTAuthenticationFiller(authenticationManager(),userService);
		filler.setAuthenticationManager(authenticationManager());
		return filler;
	}
}
