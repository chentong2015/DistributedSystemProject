public class BaseRPC {

    // TODO: 实现跨程序(部署在不同机器)方法之间的调用: HTTP，TCP，Rocket也是一种RPC协议
    // RPC: remote procedure call 远程过程调用
    // 1. 一种计算机"通讯协议"
    // 2. 该协议允许一台计算机程序调用另一台计算机子程序，无需为这个交互编程
    // 3. 如果使用面向对象编程，也可以成为远程调用或者"远程方法调用"
    //
    // 作为架构微服务化的基础组件，降低了架构微服务化成本，提高调用方与服务提供方研发效率，
    // 屏蔽跨进程调用函数(服务)的复杂细节，让调用方感觉就像调用本地函数一样调用远端函数

    // 成熟RPC框架：
    // 1. dubbo：Apache Dubbo是一款高性能、轻量级的开源服务框架
    // 2. gRPC：Google开源，具有平台无关性，基于http/2协议，支持服务追踪、负载均衡、健康检查等功能；
    //          框架是基于HTTP协议实现的，底层使用到了Netty框架的支持
    // 3. Thrift
    //    框架内部维护请求的协议和请求号，维护一个以请求号为key，结果的result为future的map
    //    结合NIO+长连接，获取非常不错的性能
}
