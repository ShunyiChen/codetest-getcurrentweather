package com.shunyi.codetest.weatherconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shunyi.codetest.common.vo.CommonResult;
import com.shunyi.codetest.common.vo.WeatherForecast;
import com.shunyi.codetest.weatherconsumer.config.SysYmlConfig;
import com.shunyi.codetest.weatherconsumer.service.WeatherService;
import com.shunyi.codetest.weatherconsumer.util.RedisUtil;
import com.shunyi.codetest.weatherconsumer.vo.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
//    private String appId = "6dcf808417595d99f60886e2f1279efe";
    @Autowired
    private SysYmlConfig sysYmlConfig;


    private final RedisUtil redisUtil;
    @Qualifier("redisTemplate")
    private final RedisTemplate redisTemplate;

    public WeatherController(RedisTemplate redisTemplate, RedisUtil redisUtil) {
        this.redisTemplate = redisTemplate;
        this.redisUtil = redisUtil;
    }

    @Autowired
    private WeatherService weatherService;

    @ApiOperation("Find a city by Name")
    @ApiImplicitParam(name = "name", value = "City Name", required = true)
    @GetMapping(value = "/weather/{name}")
    public CommonResult getCurrentWeatherData(@PathVariable("name") String cityName) {
        if(StringUtils.isEmpty(cityName)) {
            log.warn("********Get current weather data failed."+"(City Name("+cityName+")");
            return CommonResult.builder().code(500).message("Get current weather data failed").data(null).build();
        }
        WeatherForecast weatherForecast = null;
        try {
            //First to find redis
            Object obj = redisUtil.get(cityName);
            if(obj != null) {
                weatherForecast = JSON.parseObject(obj.toString(), new TypeReference<WeatherForecast>() {});
            } else {
                //If redis does not exist this value, call remote API
                weatherForecast = weatherService.getCurrentWeatherData(cityName, sysYmlConfig.getAppId());
            }
            if(weatherForecast == null) {
                log.warn("********OpenWeather calling failed."+"(appId("+sysYmlConfig.getAppId()+")");
                return CommonResult.builder().code(500).message("OpenWeather calling failed.").data(weatherForecast).build();
            } else {
                return CommonResult.builder().code(200).message("Get current weather data success").data(weatherForecast).build();
            }
        } catch (Exception e) {
            log.error("********OpenWeather calling failed and throw an exception."+"appId:"+sysYmlConfig.getAppId()+",exception:"+e);
            return CommonResult.builder().code(500).message("OpenWeather catch an exception:"+e.getMessage()).data(weatherForecast).build();
        }
    }
}
