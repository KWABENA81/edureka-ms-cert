package com.edu.hystrix.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/iss")
public class IssuerController {
    @GetMapping("/issue")
    @HystrixCommand(fallbackMethod = "doDummyIssue",
            commandProperties = {
                    @HystrixProperty(
                            name = "circuitBreaker.requestVolumeThreshold",
                            value = "6"),
                    @HystrixProperty(
                            name = "circuitBreaker.sleepWindowInMilliseconds",
                            value = "10000"),
                    @HystrixProperty(
                            name = "circuitBreaker.enabled",
                            value = "false")
            })
    public String doIssue() {
        System.out.println("...Start of ISSUE METHOD ---");
        if (new Random().nextInt(1011) <= 10) {
            throw new RuntimeException("DUMMY Example");
        }
        System.out.println("== END OF ISSUE METHOD --");
        return "SUCCESS";
    }

    public String doDummyIssue() {
        System.out.println("...FORM FALLBACK METHOD ...");
        return "SERVICE IS DOWN, TRY AGAIN";
    }
}
