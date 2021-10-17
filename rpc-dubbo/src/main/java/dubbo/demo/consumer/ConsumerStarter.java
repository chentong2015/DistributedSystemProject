package dubbo.demo.consumer;

import dubbo.demo.consumer.client.HttpClient;
import dubbo.demo.consumer.proxy.ProxyFactory;
import dubbo.demo.model.Invocation;
import dubbo.demo.provider.services.MyService;

public class ConsumerStarter {

    public static void main(String[] args) {
        testGetServiceWithProxy();
    }

    // ʹ��HttpЭ�飬ͨ������ָ����"����Э��(��Ϣ)"����ȡ�������ṩ��ָ������Ľ��
    public static void testGetService() {
        Invocation invocation = new Invocation();
        invocation.setInterfaceName(MyService.class.getName());
        invocation.setMethodName("getServiceInfo");
        invocation.setParamTypes(new Class[]{String.class});
        invocation.setParamValues(new Object[]{"consumer infos"});

        HttpClient client = new HttpClient();
        String result = client.send("localhost", 8080, invocation);
        System.out.println(result);
    }

    public static void testGetServiceWithProxy() {
        // �õ�ʵ����ָ���ӿڵĴ������
        MyService myService = ProxyFactory.getProxy(MyService.class);
        // ͨ�����������÷���������õ�InvocationHandler.invoke()����
        String result = myService.getServiceInfo("Test Proxy");
        System.out.println(result);
    }
}
