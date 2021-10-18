package dubbo.demo.consumer.proxy;

import dubbo.demo.protocol.base.ProtocolClient;
import dubbo.demo.protocol.dubbo.NettyClient;
import dubbo.demo.protocol.http.HttpClient;

import java.lang.reflect.Proxy;

// �������ڲ�ͬЭ��Ĵ������(��InvocationHandler) ==> ��Ҫ����Э������չ !!
public class ProxyFactory {

    // ʹ��JDKʵ�ֵĶ�̬������ĳһ���ӿ�����һ��������
    public static <T> T getProxyWithHttpProtocol(Class interfaceClass) {
        HttpClient httpClient = new HttpClient();
        return createNewProxy(interfaceClass, httpClient);
    }

    public static <T> T getProxyWithDubboProtocol(Class interfaceClass) {
        NettyClient nettyClient = new NettyClient();
        return createNewProxy(interfaceClass, nettyClient);
    }

    private static <T> T createNewProxy(Class interfaceClass, ProtocolClient client) {
        MyInvocationHandler invocationHandler = new MyInvocationHandler(interfaceClass, client);
        Object result = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, invocationHandler);
        return (T) result;
    }
}
