package Redis_Master.Redis_Persistence;

// Redis持久化的几种方式，优缺点是什么，怎么实现的？
// 1. RDB: ==> 指定的时间间隔执行数据集的时间点快照
//    RDB非常适合灾难恢复, 与AOF相比, 更快地重新启动大数据集
//    Redis父进程派生(fork)一个将完成所有其余工作的子进程, 父实例永远不会执行磁盘I/O或类似操作
//    如果数据集很大, Fork()可能会很耗时, 如果数据集很大且CPU性能不是很好

// 2. AOF: ==> fsync 策略
//    AOF日志是仅附加日志
//    当AOF变得太大时，Redis能够在后台自动重写
//    AOF 文件通常比相同数据集的等效 RDB 文件大, AOF可能比RDB慢

// 3. No persistence
// 4. RDB + AOF
public class RedisPersistence {

}
