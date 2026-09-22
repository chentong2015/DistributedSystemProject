import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

// TODO: 分布式锁的高并发场景: 避免"锁永久失效"的问题，根本无法保证执行的顺序和逻辑
//  > 使用Redisson
//  > 解决办法：必须保证释放的锁是自己获取的锁，通过锁的值来确定
// 1. 如何设置有效的超时时间，保证同一时间只有一个线程在执行同步的业务代码块
//    而不是可能有多个线程都在获取锁之后执行中
//    不能将过期时间设置过长，否则在某种场景下需要等待锁释放的时间过长，影响体验
// 2. 请求线程    A    B     C
//    执行时间  15S    8S   5S
//             10S   go.
//             5S    5S   go.
//                   3S   3S
public class DistributedLock3 {

    String key = "lockKey";
    StringRedisTemplate stringRedisTemplate;

    // TODO. 场景分析:
    // 由于设置了锁的过期时间，当业务的执行时间和锁的过期时间不一致时
    // 在A请求的业务执行没有结束时锁被释放，这时B线程能够获得锁，开始执行
    // 在A请求的业务完全结束时，执行finally语句，把B获得的线程锁给释放掉，然后让C请求来获得锁
    // 接着等B请求执行完，执行finally语句，又释放掉别的线程获得的锁....
    public String testDistributedLockSolution() {
        // TODO. 为每个线程创建它的ID标识 => Thread.currentThread().getId()等效
        String threadId = UUID.randomUUID().toString();
        Boolean isGetLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, threadId, 10, TimeUnit.SECONDS);
        if (Boolean.FALSE.equals(isGetLocked)) {
            return "Error";
        }

        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock - 1));
            }
        } finally {
            // TODO: 判断只能释放字节线程获取的锁，执行必须是原子执行 !!
            if (stringRedisTemplate.opsForValue().get(key).equals(threadId)) {
                // 如果系统在这里卡顿，刚好超过10s，由于之前设置的超时时间，导致锁释放了
                // 下面操作失效: 通过key来删除，有可能会删除到其他请求刚获取的锁...
                stringRedisTemplate.delete(key);
            }
        }
        return "Success";
    }
}
