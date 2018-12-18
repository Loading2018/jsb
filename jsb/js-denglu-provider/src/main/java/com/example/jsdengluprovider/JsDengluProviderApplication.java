package com.example.jsdengluprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.jsdengluprovider.dao")
public class JsDengluProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsDengluProviderApplication.class, args);
    }
}
