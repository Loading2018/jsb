package com.example.jsspringzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class  JsSpringZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsSpringZuulApplication.class, args);
    }
}
