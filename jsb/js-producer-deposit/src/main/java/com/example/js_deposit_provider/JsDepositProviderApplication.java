package com.example.js_deposit_provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.js_deposit_provider.dao")
@EnableScheduling
@EnableFeignClients
public class JsDepositProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsDepositProviderApplication.class, args);
    }
}
