import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

// TODO. 分布式锁的优化
// 1. 在主体业务抛出异常的情况下，保证拿到锁的线程必须能够释放锁
// 2. 在主体业务执行的过程，如果服务宕机或者挂掉，如何保存将获得的锁释放掉
//    - 执行finally的程序
//    - 设置key的超时时间，确保释放
// 3. 如果在设置超时时间完成之前，服务宕机或者挂掉，依然无法保证锁的释放
//    - 在获取锁和设置锁所执行的操作不是原子的，无法保证不被打断
public class DistributedLock2 {

    String key = "lockKey";
    StringRedisTemplate stringRedisTemplate;

    public String testDistributedLockPlus() {
        // boolean isGetLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, "myValue");
        // stringRedisTemplate.expire(key, 10, TimeUnit.SECONDS);

        // 将两个操作合并到一个原子操作中
        boolean isGetLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, "myValue", 10, TimeUnit.SECONDS);
        if (!isGetLocked) {
            return "Error";
        }
        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock - 1));
            }
        } finally {
            stringRedisTemplate.delete(key);
        }
        return "Success";
    }
}
