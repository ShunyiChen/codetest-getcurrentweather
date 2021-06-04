package com.shunyi.codetest.weatherconsumer.service;

import com.shunyi.codetest.common.vo.WeatherForecast;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Shunyi Chen
 * @create 2021-06-04 11:57
 */
@Component
@FeignClient(url = "${weatherforecast.url}",name="weatherforecast")
public interface WeatherService {

    @GetMapping(value = "/data/2.5/weather")
    WeatherForecast getCurrentWeatherData(@RequestParam("q") String cityName, @RequestParam("appid") String appId);
}