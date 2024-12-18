package com.pen.securitymanager.config;

import com.towpen.base.exceptions.rest.ApiErrorBeanController;
import com.towpen.base.security.filter.JwtXUserInfoFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String METHOD_AUTHENTICATE = "/authenticate";
	private static final String METHOD_SSO_LOG = "/sso-log";
	private static final String METHOD_REFRESH_TOKEN = "/refresh-token";
	private static final String METHOD_LDAP_AUTHENTICATE = "/ldap-authentication";

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, @Autowired ApiErrorBeanController apiBeanController) throws Exception {
		http.csrf().disable();
		http.cors().disable();
		http = http.authorizeHttpRequests().requestMatchers(METHOD_AUTHENTICATE, METHOD_REFRESH_TOKEN, METHOD_LDAP_AUTHENTICATE, METHOD_SSO_LOG).permitAll().and();
		http = http.authorizeHttpRequests().requestMatchers("/swagger-ui/**").permitAll().and();
		http = http.authorizeHttpRequests().requestMatchers("/actuator/**").permitAll().and();
		http = http.authorizeHttpRequests().requestMatchers("/h2-console/**").permitAll().and();
		http = http.authorizeHttpRequests().requestMatchers("/api/**").authenticated().and();
		http = http.cors().and().authorizeHttpRequests().requestMatchers("/v3/api-docs/**").permitAll().and();
		http = http.addFilterBefore(new JwtXUserInfoFilter(apiBeanController), BasicAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/swagger-ui/**", "/error", "/v3/**","/h2-console/**");
	}
}
