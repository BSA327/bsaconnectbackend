package com.company.bsaadmin.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter{

	private final JwtTokenProvider jwtTokenProvider;

    // Constructor accepting JwtTokenProvider
    public JwtAuthFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
	
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		// Check if the header starts with "Bearer "
		if (header != null) {
			String [] elements=header.split(" ");
			if(elements.length==2 && "Bearer".equals(elements[0]))
			{
				try {
					if (StringUtils.hasText(elements[1]) && jwtTokenProvider.validateToken(elements[1]) && !jwtTokenProvider.isExpired(elements[1])) {
						String username = jwtTokenProvider.getUserIdFromJWT(elements[1]);
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null
								, null);
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}else {
						SecurityContextHolder.clearContext();
						throw new BadCredentialsException("Token is not valid.");
					}

				}catch (RuntimeException e) {
					SecurityContextHolder.clearContext();
					throw e;
				}
			}


		}


		// Continue the filter chain
		filterChain.doFilter(request, response);

	}


	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getRequestURI().startsWith("/bsaadmin/api/auth/");
	}





}