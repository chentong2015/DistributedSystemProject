package dubbo.demo.provider;

import dubbo.demo.framework.data_model.URL;
import dubbo.demo.framework.factory.ProtocolFactory;
import dubbo.demo.framework.protocol.Protocol;
import dubbo.demo.register.LocalRegister;
import dubbo.demo.register.RemoteRegister;

public class ServiceProvider {

    public static void main(String[] args) {
        // 1. ����ע�� {������: ʵ����}
        LocalRegister.register(MyService.class.getName(), MyServiceImpl.class);

        // 2. Զ��ע������ע�� {������: List<URL>}
        URL url = new URL("localhost", 8080); // ����ʹ�ñ�����ַ
        RemoteRegister.register(MyService.class.getName(), url);

        // 3. ������ͬЭ���µ�Server�����ṩ��, ͨ������ģʽ��̬����
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.startServer(url);
    }
}
