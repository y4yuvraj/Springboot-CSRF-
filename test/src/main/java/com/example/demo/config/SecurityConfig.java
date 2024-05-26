package com.example.demo.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		CsrfTokenRequestAttributeHandler handler= new CsrfTokenRequestAttributeHandler();
		handler.setCsrfRequestAttributeName("_csrf");
		
		http
		  .securityContext((context) -> context.requireExplicitSave(false))
          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
          .authorizeHttpRequests(request->request
//				.requestMatchers("/m1").permitAll()
				.anyRequest()
				.authenticated())
		.csrf(csrf->csrf
				.csrfTokenRequestHandler(handler)
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		.cors(cors->cors.configurationSource( new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub
				CorsConfiguration config= new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
//				config.setExposedHeaders(Collections.singletonList("Authorization"));
				return config;
			}
		}))
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults())
		.addFilterAfter(new CsrfTokenCustomFilter(), BasicAuthenticationFilter.class);
		return http.build();
	}

}
