package org.example.controller;

import org.example.helper.SpringRedisHelper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachingController {

    private final StringRedisTemplate stringRedisTemplate = SpringRedisHelper.getStringJedisTemplate();

    @GetMapping("/cache")
    public String getCacheValue() {
        stringRedisTemplate.opsForValue().set("newKey", "newValue");

        String value = stringRedisTemplate.opsForValue().get("key1");
        System.out.println(value);
        return value;
    }
}
