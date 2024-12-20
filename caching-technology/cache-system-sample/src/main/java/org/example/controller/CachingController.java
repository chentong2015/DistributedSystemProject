package org.example.controller;

import java.util.List;
import java.util.Map;

import org.example.helper.SpringRedisHelper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachingController {

    @GetMapping("/cache")
    public String getCacheValue() {
        StringRedisTemplate redisTemplate = SpringRedisHelper.getStringJedisTemplate();

        // API for String value
        redisTemplate.opsForValue().set("newKey", "newValue");
        String value = redisTemplate.opsForValue().get("key1");
        System.out.println(value);

        // API for List values
        List<String> list = redisTemplate.boundListOps("myList").range(0, -1);
        for (String item: list) {
            System.out.println(item);
        }

        // API for HashMap values
        Map<Object, Object> map = redisTemplate.boundHashOps("myHash").entries();
        for (Map.Entry<Object, Object> entry: map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        return value;
    }
}
