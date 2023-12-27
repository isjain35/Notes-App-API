package com.example.notes.repository;

import java.util.concurrent.TimeUnit;

public interface RedisRepository {
    String saveKey(String key, String value);
    String saveExpiringKey(String key, String value, int time, TimeUnit unit);
    String retrieveKey(String key);
    String saveLog(String time, String value);
}
