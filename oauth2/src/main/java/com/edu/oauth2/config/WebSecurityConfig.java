package com.edu.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SuppressWarnings("deprecation")
@Order(SecurityProperties.BASIC_AUTH_ORDER - 6)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("Edureka")
                .password("edureka@")
                .roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().permitAll();

//                .authorizeRequests().anyRequest().authenticated().and()
//                .formLogin().loginPage("/login").permitAll();
    }

//    @Bean
//    public UserDetailsService users() {
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public UserDetailsService uds() {
        UserDetails john = User.withUsername("john").password("123").roles("USER")
         .authorities("read").build();
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2b$12$tcoaaq3PeqkerTb8OS2t5eTBKZFzrnFPJy.s1Fk8OcIZg0aQvUyeq")
                .roles("USER").authorities("read")
                .build();
        UserDetails edureka = User.builder()
                .username("Edureka")
                .password("{bcrypt}$2b$12$tcoaaq3PeqkerTb8OS2t5eTBKZFzrnFPJy.s1Fk8OcIZg0aQvUyeq")
                .roles("USER").authorities("read")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2b$12$tcoaaq3PeqkerTb8OS2t5eTBKZFzrnFPJy.s1Fk8OcIZg0aQvUyeq")
                .roles("USER", "ADMIN").authorities("read")
                .build();
        return new InMemoryUserDetailsManager(john, user, edureka, admin);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
}
