package com.example.jsproducerloans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.jsproducerloans.dao")
@EnableScheduling
public class JsProducerLoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsProducerLoansApplication.class, args);
    }
}
