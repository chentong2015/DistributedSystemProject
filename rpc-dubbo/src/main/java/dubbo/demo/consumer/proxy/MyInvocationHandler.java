package dubbo.demo.consumer.proxy;

import dubbo.demo.consumer.client.HttpClient;
import dubbo.demo.model.Invocation;
import dubbo.demo.model.URL;
import dubbo.demo.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Class interfaceClass;

    public MyInvocationHandler(Class interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation();
        invocation.setInterfaceName(interfaceClass.getName());
        invocation.setMethodName(method.getName()); // �õ������÷���������
        invocation.setParamTypes(method.getParameterTypes()); // �����÷����Ĳ����б�
        invocation.setParamValues(args); // ���������ݵ�ʵ�ʲ���

        HttpClient client = new HttpClient();
        // TODO: ����ĵ�ַ����д������Ҫ��ע�����Ķ�̬��ȡ�������������ؾ���
        // client.send("localhost", 8080, invocation);
        URL remoteAddress = RemoteRegister.getRandomURL(interfaceClass.getName());
        return client.send(remoteAddress.getHostname(), remoteAddress.getPort(), invocation);
    }
}
