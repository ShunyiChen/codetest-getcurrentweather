package com.shunyi.codetest.weatherconsumer.controller;

import com.shunyi.codetest.common.vo.CommonResult;
import com.shunyi.codetest.common.vo.WeatherForecast;
import com.shunyi.codetest.weatherconsumer.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 15:54
 */
@Api(tags = "天气管理")
@RestController
@Slf4j
@CrossOrigin(maxAge = 3600)
public class WeatherController {

//    @Value("${server.appId}")
    private String appId = "6dcf808417595d99f60886e2f1279efe";

    @Autowired
    private WeatherService weatherService;

    @ApiOperation("Find a city by Name")
    @ApiImplicitParam(name = "name", value = "City Name", required = true)
    @GetMapping(value = "/weather/{name}")
    public CommonResult getCurrentWeatherData(@PathVariable("name") String cityName) {
        if(StringUtils.isEmpty(cityName)) {
            log.warn("********Get current weather data failed."+"(City Name("+cityName+")");
            return CommonResult.builder().code(500).message("Get current weather data failed").data(null).build();
        } else {
            WeatherForecast weatherForecast = null;
            try {
                weatherForecast = weatherService.getCurrentWeatherData(cityName, appId);
                if(weatherForecast == null) {
                    log.warn("********OpenWeather calling failed."+"(appId("+appId+")");
                    return CommonResult.builder().code(500).message("OpenWeather calling failed.").data(weatherForecast).build();
                }
            } catch (Exception e) {
                log.error("********OpenWeather calling failed and throw an exception."+"appId:"+appId+",exception:"+e);
            }
            return CommonResult.builder().code(200).message("Get current weather data success").data(weatherForecast).build();
        }
    }
}
