package com.yuv.csrf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(req->req
				.anyRequest().permitAll()).build();
	}

}
