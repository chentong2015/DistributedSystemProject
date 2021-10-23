package distributed_transactions;

// TODO: 事务的理解(local transaction)
// 1. Business在同一个DB的同一个连接情况下，才是同一个事务
// 2. 事务是属于连接的

// 分布式事务的理解:
// 1. 一个事务如果跨了多个分布式搭建的数据库，如何保证事务的执行
// 2. A distributed transaction will ensure that all records get updated, otherwise rollback
public class BaseDistributedTransaction {

    // @Transactional注解做了那些事情? 如何基于该注解来手写分布式事务框架? 如何拿到事务的控制权，控制什么时时候提交
    // 1. 建立链连接
    // 2. 开启事务
    // 3. 执行方法
    // 4. 提交或回滚

    // 测试场景: 在执行server1的业务逻辑时，需要在一个事务中调用server2的业务逻辑
    // Server1: @Service
    // @Transactional
    // public void test() {
    //    daoObject.insert("server1"); 在server1的DB中插入数据
    //    HttpClient.get("http://127.0.0.1:8082/server2/test");
    //    int i = 10 / 0;
    //    TODO: 由于异常导致事务的回滚，server1 DB中插入数据回滚，但是server2的DB中插入数据会被保留
    // }
    //
    // Server2: @Service
    // @Transactional
    // public void test() {
    //    daoObject.insert("server2"); 在server2的DB中插入数据
    // }
}
