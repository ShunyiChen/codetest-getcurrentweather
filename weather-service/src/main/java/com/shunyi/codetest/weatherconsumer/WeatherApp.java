package com.shunyi.codetest.weatherconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 15:48
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class WeatherApp {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApp.class, args);
    }
}
