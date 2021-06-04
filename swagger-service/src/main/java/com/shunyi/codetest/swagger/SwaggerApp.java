package com.shunyi.codetest.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Shunyi Chen
 * @create 2021-06-04 7:16
 */
@SpringBootApplication
@EnableEurekaClient
public class SwaggerApp {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApp.class, args);
    }
}
