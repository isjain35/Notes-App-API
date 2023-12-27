package com.example.notes.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisRepositoryImpl implements RedisRepository{

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public String saveKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "Saved "+key+": "+value;
    }

    @Override
    public String saveExpiringKey(String key, String value, int time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
        return "Saved "+key+": "+value+" for "+time+" "+unit;
    }

    @Override
    public String retrieveKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public String saveLog(String time, String value) {
        redisTemplate.opsForHash().put("Logs", time, value);
        return "Saved a log.";
    }
}
