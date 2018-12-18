package com.example.jsproducerfund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JsProducerFundApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsProducerFundApplication.class, args);
    }
}
