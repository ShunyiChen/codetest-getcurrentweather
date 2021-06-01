package com.shunyi.codetest.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Shunyi Chen
 * @create 2021-05-24 10:20
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApp {

    public static void main(String[] arguments) {
        SpringApplication.run(ConfigServerApp.class, arguments);
    }
}
