package com.shunyi.codetest.weatherconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Shunyi Chen
 * @create 2021-06-11 9:31
 */
@Configuration
public class AppContextConfig {

    @Bean
//    @LoadBalanced  注释掉是为了解决No instances available for CITY-SERVICE
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
