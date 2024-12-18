package problems;

import problems.model.Order;
import problems.model.OrderService;
import org.springframework.data.redis.core.StringRedisTemplate;
import rest_controller.SpringRedisHelper;

import java.util.concurrent.TimeUnit;

public class RedisCachingDemo {

    private StringRedisTemplate redisTemplate = SpringRedisHelper.getStringJedisTemplate();

    // TODO. 1. 错误代码演示
    public String findOrder(int id) {
        // 1. 查询缓存
        Object cacheOrder = redisTemplate.opsForValue().get(String.valueOf(id));
        if (cacheOrder != null) {
            return "find order in cache";
        }
        // 2. 查询数据库
        Order order = OrderService.selectOrderById(id);
        if (order != null) {
            // 3. 加入缓存
            redisTemplate.opsForValue().set(String.valueOf(id), order.getName());
            return "get order from db";
        }
        return "find nothing";
    }

    // TODO: 2. 缓存空对象的实现: 当查询不存在的数据时，在缓存中添加空对象做判断
    // 1. 空的对象会占用redis的内存空间
    // 2. 一旦超过过期时间，redis会将其清除，之后必须再次查询数据库
    public String findOrder2(int id) {
        // 1. 查询缓存
        Object cacheOrder = redisTemplate.opsForValue().get(String.valueOf(id));
        if (cacheOrder != null) {
            // 判断查询的是否是缓存的空对象, 如果是，则不用再查询数据库
            // if(cacheOrder is null object) {
            //    return "find nothing";
            // }
            return "find order in cache";
        }

        // 2. 查询数据库
        Order order = OrderService.selectOrderById(id);
        if (order != null) {
            // 3. 加入缓存
            redisTemplate.opsForValue()
                    .set(String.valueOf(id), order.getName(), 10, TimeUnit.MINUTES);
            return "get order from db";
        } else {
            // 如果在数据库中没有查到，则设置空对象到缓存中
            redisTemplate.opsForValue()
                    .set(String.valueOf(id), "null object", 10, TimeUnit.MINUTES);
        }
        return "find nothing";
    }

}

