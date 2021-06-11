package com.shunyi.codetest.weatherconsumer.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shunyi.codetest.common.vo.CommonResult;
import com.shunyi.codetest.common.vo.WeatherForecast;
import com.shunyi.codetest.weatherconsumer.config.SysYmlConfig;
import com.shunyi.codetest.weatherconsumer.service.WeatherService;
import com.shunyi.codetest.weatherconsumer.util.RedisUtil;
import com.shunyi.codetest.weatherconsumer.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shunyi Chen
 *
 * @create 2021-06-11 9:05
 */
@Component
@Slf4j
public class ScheduledTask {
    /** Url of city service */
    private static final String CITY_SERVICE_URL = "http://172.19.249.254:33472";
    /** Redis key's expire time is 60 seconds */
    private final long timeout = 60;
    /** Redis util */
    private final RedisUtil redisUtil;
    @Autowired
    private SysYmlConfig sysYmlConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherService weatherService;
    @Qualifier("redisTemplate")
    private final RedisTemplate redisTemplate;

    /**
     * Constructor
     *
     * @param redisTemplate
     * @param redisUtil
     */
    public ScheduledTask(RedisTemplate redisTemplate, RedisUtil redisUtil) {
        this.redisTemplate = redisTemplate;
        this.redisUtil = redisUtil;
    }

    /**
     * Cache the weather forecast data per minute
     */
    @Scheduled(cron = "0 * * * * ? ")
    public void scheduledTask() {
        //Call city-service to get the city list
        CommonResult commonResult = restTemplate.getForObject(CITY_SERVICE_URL+"/city/findall", CommonResult.class);
        if(commonResult != null) {
            //Convert data in json format
            String json = JSON.toJSONString(commonResult.getData());
            //Parse the city list from json data
            List<City> cityArrayList = JSON.parseObject(json, new TypeReference<ArrayList<City>>() {});
            cityArrayList.forEach(e -> {
                WeatherForecast weatherForecast = weatherService.getCurrentWeatherData(e.getName(), sysYmlConfig.getAppId());
                redisUtil.set(e.getName(), JSON.toJSONString(weatherForecast));
                redisUtil.expire(e.getName(), timeout);
                System.out.println("set "+redisUtil.get(e.getName()));
                log.info("********Set "+e.getName()+"->"+redisUtil.get(e.getName()));
            });
        } else {
            log.warn("********No city found.");
        }
    }
}
