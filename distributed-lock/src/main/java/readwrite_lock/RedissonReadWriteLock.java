package readwrite_lock;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.data.redis.core.StringRedisTemplate;

// TODO. 分布式系统高并发请求: 分布式读写锁
public class RedissonReadWriteLock {

    private Redisson redisson;
    private RReadWriteLock readWriteLock;
    private StringRedisTemplate stringRedisTemplate;

    private final String lockKey = "keyProduct101";

    public RedissonReadWriteLock() {
        this.readWriteLock = redisson.getReadWriteLock(lockKey);
    }

    // Read + Write: 查询并添加缓存数据
    public String getStock(Long clientId) {
        RLock readLock = readWriteLock.readLock();
        readLock.lock();

        String stock = stringRedisTemplate.opsForValue().get("stock");
        if (StringUtil.isEmpty(stock)) {
            System.out.println("Search database 10");
            stringRedisTemplate.opsForValue().set("stock", "10");
        }

        readLock.unlock();
        return "end";
    }

    // Write: 更改缓存缓存数据 > 添加写锁来支撑高并发Update修改
    public String updateStock(Long clientId) {
        RLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        stringRedisTemplate.delete("stock");

        writeLock.unlock();
        return "end";
    }
}
