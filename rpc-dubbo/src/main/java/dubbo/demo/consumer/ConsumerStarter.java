package dubbo.demo.consumer;

import dubbo.demo.consumer.proxy.ProxyFactory;
import dubbo.demo.model.Invocation;
import dubbo.demo.protocol.http.HttpClient;
import dubbo.demo.provider.services.MyService;

public class ConsumerStarter {

    public static void main(String[] args) {
        getServiceWithDubboProxy();
    }

    // ����HttpЭ�飬ͨ������ָ����"����Э��(��Ϣ)"����ȡ�������ṩ��ָ������Ľ��
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

    // ʹ�ö�̬��������HttpͨѶЭ��
    public static void getServiceWithHttpProxy() {
        // �õ�ʵ����ָ���ӿڵĴ������
        MyService myService = ProxyFactory.getProxyWithHttpProtocol(MyService.class);
        // ͨ�����������÷�����ʵ�ʻ���õ�InvocationHandler.invoke()����
        String result = myService.getServiceInfo("http Proxy");
        System.out.println(result);
    }

    // ʹ�ö�̬��������DubboͨѶЭ��
    public static void getServiceWithDubboProxy() {
        MyService myService = ProxyFactory.getProxyWithDubboProtocol(MyService.class);
        String result = myService.getServiceInfo("dubbo Proxy");
        System.out.println(result);
    }
}
