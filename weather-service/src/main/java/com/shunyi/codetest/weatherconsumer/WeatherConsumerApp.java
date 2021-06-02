package com.shunyi.codetest.weatherconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 15:48
 */
@SpringBootApplication
@EnableEurekaClient
public class WeatherConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(WeatherConsumerApp.class, args);
    }
}
