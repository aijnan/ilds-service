package com.example.wms.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = { "com.example.wms" })
@EnableFeignClients(basePackages = {"com.example.wms.api.**.feign"})
public class WmsBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(WmsBaseApplication.class, args);
    }
}