package dubbo.demo.provider;

import dubbo.demo.protocol.http.HttpServer;

public class Provider {

    public static void main(String[] args) {
        // 1. ����ע��

        // 2. Զ��ע������ע��

        // 3. ����Tomcat
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8000);
    }
}
