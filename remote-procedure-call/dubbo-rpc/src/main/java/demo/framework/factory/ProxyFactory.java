package dubbo.demo.framework.factory;

import dubbo.demo.framework.data_model.Invocation;
import dubbo.demo.framework.data_model.URL;
import dubbo.demo.framework.protocol.Protocol;
import dubbo.demo.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    // ʹ�ù���ģʽ����̬ȷ��ʹ�õ�Э�飬����Ծ������͵�ǿ����(���)
    private static Protocol protocol;

    // ʹ��JDKʵ�ֵĶ�̬������ĳһ���ӿ�����һ��������
    public static <T> T getProxy(Class interfaceClass) {
        protocol = ProtocolFactory.getProtocol();
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass}, getInvocationHandler(interfaceClass));
    }

    // ʹ���������ͣ�ֱ���ڷ�������ʵ�ֽӿڣ������������������
    private static InvocationHandler getInvocationHandler(Class interfaceClass) {
        return (proxy, method, args) -> {
            Invocation invocation = new Invocation();
            invocation.setInterfaceName(interfaceClass.getName());
            invocation.setMethodName(method.getName()); // �õ������÷���������
            invocation.setParamTypes(method.getParameterTypes()); // �����÷����Ĳ����б�
            invocation.setParamValues(args); // ���������ݵ�ʵ�ʲ���
            // TODO: ʹ�õķ����ṩ�ߵĵ�ַ����д������Ҫ��ע�����Ķ�̬ȡ����(�����ؾ���)
            URL remoteAddress = RemoteRegister.getRandomURL(interfaceClass.getName());
            return protocol.sendInvocation(remoteAddress, invocation);
        };
    }
}
