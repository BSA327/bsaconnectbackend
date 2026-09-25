package com.company.bsaadmin.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationEnryPoint implements AuthenticationEntryPoint{
	
	private static final ObjectMapper OBJECT_MAPPER =new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDto("Not Auhtorized"));
		
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		
	}

}
