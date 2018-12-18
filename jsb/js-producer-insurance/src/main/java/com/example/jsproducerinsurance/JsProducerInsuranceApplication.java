package com.example.jsproducerinsurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JsProducerInsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsProducerInsuranceApplication.class, args);
    }
}
