package com.edu.oauth2.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SuppressWarnings("deprecation")
@Order(SecurityProperties.BASIC_AUTH_ORDER - 6)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService uds() {
        UserDetails john = User.withUsername("john").password("passw0rd123")     //.roles("USER")
                .authorities("read").build();
        UserDetails user = User.withUsername("user").password("passw0rd123")
                //.password("{bcrypt}$2b$12$tcoaaq3PeqkerTb8OS2t5eTBKZFzrnFPJy.s1Fk8OcIZg0aQvUyeq").roles("USER")
                .authorities("read").build();
        UserDetails edureka = User.withUsername("Edureka").password("passw0rd123")
                //.password("{bcrypt}$2b$12$tcoaaq3PeqkerTb8OS2t5eTBKZFzrnFPJy.s1Fk8OcIZg0aQvUyeq").roles("USER")
                .authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("passw0rd123")
                //.password("{bcrypt}$2b$12$tcoaaq3PeqkerTb8OS2t5eTBKZFzrnFPJy.s1Fk8OcIZg0aQvUyeq").roles("USER", "ADMIN")
                .authorities("read").build();
        return new InMemoryUserDetailsManager(john, user, edureka, admin);
        // return new InMemoryUserDetailsManager(john);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().permitAll();
//                .authorizeRequests().anyRequest().authenticated().and()
//                .formLogin().loginPage("/login").permitAll();
    }
}
//    @Override         DUPLICATE CODE??
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password("password")
//                .roles("USER")
//                .and()
//                .withUser("Edureka")
//                .password("edureka@")
//                .roles("ADMIN");
//    }

//    @Bean
//    public UserDetailsService users() {
//        return new InMemoryUserDetailsManager(user, admin);  }

//    @Autowired         DUPLICATE CODE??
//    public void globalUserDetails(final AuthenticationManagerBuilder builder) throws Exception {
//        builder.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("ADMIN");
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

