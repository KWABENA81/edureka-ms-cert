package com.edu.issuerms;

//import io.prometheus.client.hotspot.DefaultExports;
//import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
//import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
//import io.prometheus.client.spring.web.EnablePrometheusTiming;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
//@EnableFeignClients
//@EnablePrometheusEndpoint
//@EnableSpringBootMetricsCollector
//@EnablePrometheusTiming
@EnableEurekaClient
@EnableWebSecurity
public class IssuermsApplication {

	public static void main(String[] args) {
		//DefaultExports.initialize();
		SpringApplication.run(IssuermsApplication.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
}
