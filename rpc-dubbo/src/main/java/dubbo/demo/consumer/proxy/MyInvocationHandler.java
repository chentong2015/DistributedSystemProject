package dubbo.demo.consumer.proxy;

import dubbo.demo.model.Invocation;
import dubbo.demo.model.URL;
import dubbo.demo.protocol.base.ProtocolClient;
import dubbo.demo.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Class interfaceClass;
    private ProtocolClient client;

    public MyInvocationHandler(Class interfaceClass, ProtocolClient client) {
        this.interfaceClass = interfaceClass;
        this.client = client;
    }

    // 1. Invocation��������ݶ�̬�ĸ��ݵ��÷���������ȡ
    // 2. TODO: ʹ�õķ����ṩ�ߵĵ�ַ����д������Ҫ��ע�����Ķ�̬ȡ����(�����ؾ���)
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation();
        invocation.setInterfaceName(interfaceClass.getName());
        invocation.setMethodName(method.getName()); // �õ������÷���������
        invocation.setParamTypes(method.getParameterTypes()); // �����÷����Ĳ����б�
        invocation.setParamValues(args); // ���������ݵ�ʵ�ʲ���
        URL remoteAddress = RemoteRegister.getRandomURL(interfaceClass.getName());
        return client.send(remoteAddress.getHostname(), remoteAddress.getPort(), invocation);
    }
}
