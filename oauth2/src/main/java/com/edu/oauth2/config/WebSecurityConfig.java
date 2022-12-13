package com.edu.oauth2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SuppressWarnings("deprecation")
@Order(SecurityProperties.BASIC_AUTH_ORDER - 6)
@Configuration
@EnableDiscoveryClient
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest()
                .authenticated().and().formLogin().loginPage("/login")
                .permitAll();
    }

    @Bean
    public UserDetailsService uds() {
        UserDetails john = User.withUsername("john")
                .password("123")
                .authorities("read")
                .build();

        InMemoryUserDetailsManager udm = new InMemoryUserDetailsManager();
        udm.createUser(john);
        return udm;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().withUser("user")
                .password("password").roles("USER")
                .and().withUser("admin").password("admin").roles("ADMIN");
    }
}
