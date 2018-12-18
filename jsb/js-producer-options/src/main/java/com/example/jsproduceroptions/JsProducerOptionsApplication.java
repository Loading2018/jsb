package com.example.jsproduceroptions;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.jsproduceroptions.dao")
public class JsProducerOptionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsProducerOptionsApplication.class, args);
    }
}
