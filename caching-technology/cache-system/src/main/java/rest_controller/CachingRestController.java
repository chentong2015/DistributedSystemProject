package rest_controller;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO. 高并发(分布式系统)请求操作缓存数据: 分布式读写锁
public class CachingRestController {

    private final String lockKey = "keyProduct101";

    @Autowired
    private Redisson redisson;
    private final RReadWriteLock readWriteLock = redisson.getReadWriteLock(lockKey);
    private final StringRedisTemplate stringRedisTemplate = SpringRedisHelper.getStringJedisTemplate();

    // 背后实现逻辑:
    // set key -> mode(read), value 对于添加的锁设置一个读写的模式
    @RequestMapping("/getStock")
    public String getStock(@RequestParam("clientId") Long clientId) throws InterruptedException {
        RLock readLock = readWriteLock.readLock();
        readLock.lock();

        // Read + Write: 查询并添加Redis缓存数据
        String stock = stringRedisTemplate.opsForValue().get("stock");
        if (StringUtil.isEmpty(stock)) {
            System.out.println("Search database 10");
            Thread.sleep(5000);
            stringRedisTemplate.opsForValue().set("stock", "10");
        }

        readLock.unlock();
        return "end";
    }

    @RequestMapping("/updateStock")
    public String updateStock(@RequestParam("clientId") Long clientId) throws InterruptedException {
        RLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        // Write: 更改Redis缓存
        stringRedisTemplate.delete("stock");

        Thread.sleep(5000);
        writeLock.unlock();
        return "end";
    }
}
