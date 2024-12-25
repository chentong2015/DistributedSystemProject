package project.controller;

import jodd.util.StringUtil;
import project.helper.SpringRedisHelper;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO. 分布式系统高并发请求操作缓存数据: 分布式读写锁
public class CachingRestController {

    private final String lockKey = "keyProduct101";

    @Autowired
    private Redisson redisson;
    private final RReadWriteLock readWriteLock;
    private final StringRedisTemplate stringRedisTemplate;

    public CachingRestController() {
        this.readWriteLock = redisson.getReadWriteLock(lockKey);
        this.stringRedisTemplate = SpringRedisHelper.getStringJedisTemplate();
    }

    // Read + Write: 查询并添加缓存数据
    @RequestMapping("/getStock")
    public String getStock(@RequestParam("clientId") Long clientId) throws InterruptedException {
        RLock readLock = readWriteLock.readLock();
        readLock.lock();


        String stock = stringRedisTemplate.opsForValue().get("stock");
        if (StringUtil.isEmpty(stock)) {
            System.out.println("Search database 10");
            Thread.sleep(5000);
            stringRedisTemplate.opsForValue().set("stock", "10");
        }

        readLock.unlock();
        return "end";
    }

    // Write: 更改缓存缓存数据 > 添加写锁来支撑高并发Update修改
    @RequestMapping("/updateStock")
    public String updateStock(@RequestParam("clientId") Long clientId) throws InterruptedException {
        RLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        stringRedisTemplate.delete("stock");

        Thread.sleep(5000);
        writeLock.unlock();
        return "end";
    }
}
