package com.shunyi.codetest.weatherconsumer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author Shunyi Chen
 * @create 2021-04-11 0:13
 */
@Component
public final class RedisUtil {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key  键
     * @param time 时间（秒）
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if(time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据Key获取过期时间
     * @param key 键不能为null
     * @return 时间（秒）返回0代表永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true存在false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可传一个或多个key
     */
    public void del(String... key) {
        if(key != null && key.length > 0) {
            if(key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 返回对应值
     */
    public Object get(String key) {
        return key == null? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Set key to hold the string value and expiration timeout if key is absent.
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    public boolean setNx(String key, Object value, long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
