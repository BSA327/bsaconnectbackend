package com.company.bsaadmin.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.setAllowedHeaders(Arrays.asList(
				HttpHeaders.AUTHORIZATION,
				HttpHeaders.CONTENT_TYPE,
				HttpHeaders.ACCEPT
				));
		config.setAllowedMethods(Arrays.asList(
				HttpMethod.GET.name(),
				HttpMethod.POST.name(),
				HttpMethod.PUT.name(),
				HttpMethod.DELETE.name()
				));
		config.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(-102);
		return bean;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Map the directory to a URL path
		registry.addResourceHandler("/uploads/**")
		.addResourceLocations("file:" + System.getProperty("user.home") + "/uploads/");
	}

}
