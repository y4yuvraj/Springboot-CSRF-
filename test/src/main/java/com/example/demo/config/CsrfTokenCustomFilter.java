package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CsrfTokenCustomFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

		if (null!=token.getHeaderName()) {
			{
				response.setHeader(token.getHeaderName(), token.getToken());
			}
			filterChain.doFilter(request, response);
		}

	}
}
