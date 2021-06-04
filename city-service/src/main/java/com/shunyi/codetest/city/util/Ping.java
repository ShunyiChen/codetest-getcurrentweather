package com.shunyi.codetest.city.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Shunyi Chen
 * @create 2021-04-10 23:01
 */
@Component
public class Ping {

    private final RedisUtil redisUtil;

    @Qualifier("redisTemplate")
    private final RedisTemplate redisTemplate;

    public Ping(RedisTemplate redisTemplate, RedisUtil redisUtil) {
        this.redisTemplate = redisTemplate;
        this.redisUtil = redisUtil;
    }

    public void startPing() {
        //通过建立连接调用其它方法
        RedisConnection conn = redisTemplate.getConnectionFactory().getConnection();
        String pong = conn.ping();
        System.out.println("输出:"+pong);
        conn.close();

        //通过自定义redis template操作
        redisTemplate.opsForValue().set("name", "Shunyi Chen");
        redisTemplate.opsForValue().set("age", "36");
        Optional.ofNullable(redisTemplate.opsForValue().get("name")).ifPresent(e -> {
            System.out.println(e.toString());
        });
        Optional.ofNullable(redisTemplate.opsForValue().get("age")).ifPresent(e -> {
            System.out.println(e.toString());
        });

        //通过util操作
        redisUtil.set("k1", "abc");
        System.out.println(redisUtil.get("k1"));
    }
}
