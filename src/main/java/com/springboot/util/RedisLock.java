package com.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis实现的简易分布式锁
 * 复杂的实现可参考 http://redis.io/topics/distlock
 */
@Component
public class RedisLock {

    private static final String LOCK_KEY_PREFIX = "lock.";

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * redis setNx的集群排它锁
     *
     * @param key
     * @param second
     * @return
     * @see <a href="http://redis.io/commands/setnx">Redis Documentation: SETNX</a>
     */
    public boolean lock(String key, long second) {
        key = LOCK_KEY_PREFIX + key;
        Boolean set = redisTemplate.opsForValue().setIfAbsent(key, System.currentTimeMillis() + second * 1000 + "");
        if (set) {
            redisTemplate.expire(key, second, TimeUnit.SECONDS);
        } else {
            String value = redisTemplate.opsForValue().getAndSet(key, System.currentTimeMillis() + second * 1000 + "");
            if (value != null && Long.parseLong(value) + 1 <= System.currentTimeMillis()) {
                return true;
            }
        }
        return set;
    }

}
