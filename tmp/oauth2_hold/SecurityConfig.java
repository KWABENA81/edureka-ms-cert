package com.edu.oauth2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.ws.rs.HttpMethod;

@Configuration
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/books", "/api/book/**")
                .hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "/api/books/")
                .hasAuthority("SCOPE_write")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        return httpSecurity.build();
    }}
