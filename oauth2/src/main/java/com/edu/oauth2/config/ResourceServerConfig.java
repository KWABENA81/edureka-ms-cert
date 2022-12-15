package com.edu.oauth2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//        httpSecurity
//                .authorizeExchange().anyExchange().authenticated()
//                .and()
//                .oauth2ResourceServer().jwt();
//        return httpSecurity.build();
//    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/oauth/token", "/customer").permitAll()
                .anyRequest().authenticated();
    }
    @Bean
    public ReactiveJwtDecoder reactiveDecoder() {
        byte[] keyInBytes = "123456789012345678901234567890AB".getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyInBytes, 0, keyInBytes.length, "AES");
        return NimbusReactiveJwtDecoder.withSecretKey(secretKeySpec).build();
    }
}
