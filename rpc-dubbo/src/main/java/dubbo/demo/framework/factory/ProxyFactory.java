package dubbo.demo.framework.factory;

import dubbo.demo.framework.data_model.Invocation;
import dubbo.demo.framework.data_model.URL;
import dubbo.demo.framework.protocol.Protocol;
import dubbo.demo.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

// ʹ��JDKʵ�ֵĶ�̬������ĳһ���ӿ�����һ��������
public class ProxyFactory {

    private static Protocol protocol;

    public static <T> T getProxy(Class interfaceClass) {
        // TODO: �����ɶ�̬����ǰ����̬ȷ��ʹ�õ�Э��
        // 1. ʹ�ù���ģʽ������Ծ������͵�ǿ����(���) > ProtocolFactory.getProtocol()
        // 2. ����SPI���ƣ���ɶ�̬����
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
