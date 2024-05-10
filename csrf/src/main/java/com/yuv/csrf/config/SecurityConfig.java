package com.yuv.csrf.config;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config= new CorsConfiguration();
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
						config.setMaxAge(3600L);
						return config;
					}
				})).authorizeHttpRequests(req -> req
						.anyRequest().permitAll())
				.build();
	}

}
