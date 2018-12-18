package com.example.jsproducercreditcard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.jsproducercreditcard.dao")
@EnableSwagger2
public class JsProducerCreditcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsProducerCreditcardApplication.class, args);
    }
}
