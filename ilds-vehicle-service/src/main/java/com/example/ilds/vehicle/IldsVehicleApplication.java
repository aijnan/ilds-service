package com.example.ilds.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = { "com.example.ilds" })
@EnableFeignClients(basePackages = {"com.example.ilds.api.**.feign"})
public class IldsVehicleApplication {
    public static void main(String[] args) {
        SpringApplication.run(IldsVehicleApplication.class, args);
    }
}