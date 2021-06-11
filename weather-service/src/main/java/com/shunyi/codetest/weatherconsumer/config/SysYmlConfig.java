package com.shunyi.codetest.weatherconsumer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Shunyi Chen
 * @create 2021-06-11 10:24
 */
@Component
@Data
public class SysYmlConfig {
    @Value("${weatherforecast.appId}")
    private String appId;
}
