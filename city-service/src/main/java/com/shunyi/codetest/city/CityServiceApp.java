package com.shunyi.codetest.city;

import com.shunyi.codetest.city.util.Ping;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 15:52
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.shunyi.codetest.city.dao")
public class CityServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(CityServiceApp.class, args);
    }

//    private final Ping ping;
//
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//            ////============================分割线
//            ping.startPing();
//
//        };
//    }

}