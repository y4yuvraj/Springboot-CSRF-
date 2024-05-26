package com.yuv.csrf.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.yuv.csrf.filter.CustomCsrfFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
//		CsrfTokenRequestAttributeHandler reqHandler = new CsrfTokenRequestAttributeHandler();
//		reqHandler.setCsrfRequestAttributeName("_csrf");
		
		return http.csrf(csrf->csrf
//				.csrfTokenRequestHandler(reqHandler)
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				)
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
				}))
				.addFilterAfter(new CustomCsrfFilter() , BasicAuthenticationFilter.class)
				.authorizeHttpRequests(req -> req.requestMatchers("messages").authenticated())
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
				.build();
		
	}

}
