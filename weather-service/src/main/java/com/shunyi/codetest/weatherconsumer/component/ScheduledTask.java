package com.shunyi.codetest.weatherconsumer.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shunyi.codetest.common.vo.CommonResult;
import com.shunyi.codetest.common.vo.WeatherForecast;
import com.shunyi.codetest.weatherconsumer.config.SysYmlConfig;
import com.shunyi.codetest.weatherconsumer.service.WeatherService;
import com.shunyi.codetest.weatherconsumer.util.RedisUtil;
import com.shunyi.codetest.weatherconsumer.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Shunyi Chen
 *
 * @create 2021-06-11 9:05
 */
@Component
public class ScheduledTask {

    public static final String PAYMENT_URL = "http://CITY-SERVICE";

    private final RedisUtil redisUtil;

    @Autowired
    private SysYmlConfig sysYmlConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherService weatherService;

    @Qualifier("redisTemplate")
    private final RedisTemplate redisTemplate;

    public ScheduledTask(RedisTemplate redisTemplate, RedisUtil redisUtil) {
        this.redisTemplate = redisTemplate;
        this.redisUtil = redisUtil;
    }

//    @Scheduled(fixedRate = 60000)

    /**
     * Search city list per minute
     */
    @Scheduled(cron = "0 * * * * ? ")
    public void scheduledTask() {
        CommonResult commonResult = restTemplate.getForObject(PAYMENT_URL+"/city/findall", CommonResult.class);
        System.out.println("任务执行时间：" + LocalDateTime.now()+"\n\n"+commonResult);

        String json = JSON.toJSONString(commonResult.getData());
        List<City> cityArrayList = JSON.parseObject(json, new TypeReference<ArrayList<City>>() {});
        cityArrayList.forEach(e -> {
            WeatherForecast weatherForecast = weatherService.getCurrentWeatherData(e.getName(), sysYmlConfig.getAppId());
//            redisUtil.setNx(e.getName(), JSON.toJSONString(weatherForecast), 120, TimeUnit.SECONDS);

            redisUtil.set(e.getName(), JSON.toJSONString(weatherForecast));
            redisUtil.expire(e.getName(), 60);

            System.out.println(redisUtil.get(e.getName()));
        });
        System.out.println("任务执行时间2：" + LocalDateTime.now()+"\n\n"+commonResult);
    }
}
