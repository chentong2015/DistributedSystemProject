package dubbo.demo.protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {

    public void start(String hostname, int port) {
        Tomcat tomcat = new Tomcat();
        tomcat.getHost().setAutoDeploy(false);
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");
        Connector connector = new Connector();
        connector.setPort(port);
        service.addConnector(connector);

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());
        Host host = new StandardHost();
        host.setName(hostname);
        host.addChild(context);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);
        engine.addChild(host);
        service.setContainer(engine);

        // Tomcat��Ҫ�ַ�������Ҫ���Servlet
        tomcat.addServlet(contextPath, "dispatcher", new MyDispatcherServlet());
        // ����ӳ���ϵ������������Ҫ�ߵ�Servlet
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
            System.out.println("Http Server(Tomcat) started at " + hostname + ":" + port);
            tomcat.getServer().await();
        } catch (LifecycleException ex) {
            ex.printStackTrace();
        }
    }
}
