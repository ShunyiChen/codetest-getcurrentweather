package com.shunyi.codetest.registerserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Shunyi Chen
 * @create 2020-12-11 23:48
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterServerApp2 {

	public static void main(String[] args) {
		System.out.println("热部署已开启2");
		SpringApplication.run(RegisterServerApp2.class, args);
	}

}
