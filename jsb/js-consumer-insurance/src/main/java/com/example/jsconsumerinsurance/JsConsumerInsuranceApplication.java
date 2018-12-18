package com.example.jsconsumerinsurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JsConsumerInsuranceApplication {

    public static void main(String[] args) {
        System.out.println(111);
        SpringApplication.run(JsConsumerInsuranceApplication.class, args);
    }
}
