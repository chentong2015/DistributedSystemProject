package dubbo.demo.consumer.proxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    // ʹ��JDKʵ�ֵĶ�̬������ĳһ���ӿ�����һ��������
    public static <T> T getProxy(Class interfaceClass) {
        MyInvocationHandler invocationHandler = new MyInvocationHandler(interfaceClass);
        Object result = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, invocationHandler);
        return (T) result;
    }
}
