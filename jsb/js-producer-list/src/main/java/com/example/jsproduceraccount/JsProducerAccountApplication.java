package com.example.jsproduceraccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JsProducerAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsProducerAccountApplication.class, args);
    }
}
