package com.edu.issuerms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableFeignClients
//@EnablePrometheusEndpoint
//@EnableSpringBootMetricsCollector
//@EnablePrometheusTiming
@EnableSwagger2
@EnableWebMvc
@EnableEurekaClient
@EnableWebSecurity
public class IssuermsApplication {

    public static void main(String[] args) {
        //DefaultExports.initialize();
        SpringApplication.run(IssuermsApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

}
