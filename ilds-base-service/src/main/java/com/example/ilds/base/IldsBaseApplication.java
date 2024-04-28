package com.example.ilds.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = { "com.example.ilds" })
@EnableFeignClients(basePackages = {"com.example.ilds.api.**.feign"})
public class IldsBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(IldsBaseApplication.class, args);
    }
}