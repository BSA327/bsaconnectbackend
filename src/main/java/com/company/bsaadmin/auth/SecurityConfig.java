package com.company.bsaadmin.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAuthenticationEnryPoint userAuthenticationEnryPoint;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        try {
            http.exceptionHandling()
                    .authenticationEntryPoint(userAuthenticationEnryPoint)
                .and()
                    .addFilterBefore(new JwtAuthFilter(jwtTokenProvider), BasicAuthenticationFilter.class)
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, 
                        "/api/auth/*").permitAll()
                    .antMatchers("/api/**").authenticated()  // Only secure your API
                    .anyRequest().permitAll();   
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
