package com.example.jsproducerfinancial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JsProducerFinancialApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsProducerFinancialApplication.class, args);
    }
}
