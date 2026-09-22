import org.springframework.data.redis.core.StringRedisTemplate;

// TODO: Redis方案: 使用SETNX来实现分布式锁
public class DistributedLock {

    String key = "lockKey";
    StringRedisTemplate stringRedisTemplate;

    public String testDistributedLock() {
        boolean isGetLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, "myValue");
        if (!isGetLocked) {
            // 没拿到锁，则从后端返回，后端业务繁忙给出提示
            return "Error: Service is busy.";
        }

        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
        if (stock > 0) {
            stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock - 1));
        }

        // 删除掉设置的key，以便于其的请求能够设置key并拿到锁
        stringRedisTemplate.delete(key);
        return "Success";
    }
}
