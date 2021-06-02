package com.shunyi.codetest.city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 15:52
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.shunyi.codetest.city.dao")
public class WeatherProducerApp {

    public static void main(String[] args) {
        SpringApplication.run(WeatherProducerApp.class, args);
    }
}