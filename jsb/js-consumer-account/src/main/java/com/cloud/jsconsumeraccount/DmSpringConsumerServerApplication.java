package com.cloud.jsconsumeraccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker//熔断
public class DmSpringConsumerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmSpringConsumerServerApplication.class, args);
    }
}
