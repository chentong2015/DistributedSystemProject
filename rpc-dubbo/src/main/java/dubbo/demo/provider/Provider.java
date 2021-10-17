package dubbo.demo.provider;

import dubbo.demo.framework.URL;
import dubbo.demo.protocol.http.HttpServer;
import dubbo.demo.provider.local.LocalRegister;
import dubbo.demo.provider.remote.RemoteRegister;
import dubbo.demo.provider.services.MyService;
import dubbo.demo.provider.services.MyServiceImpl;

public class Provider {

    public static void main(String[] args) {
        // 1. ����ע�� {������: ʵ����}
        LocalRegister.register(MyService.class.getName(), MyServiceImpl.class);

        // 2. Զ��ע������ע�� {������: List<URL>}
        URL url = new URL("localhost", 8080); // ����ʹ�ñ�����ַ
        RemoteRegister.register(MyService.class.getName(), url);

        // 3. ����Tomcat
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
