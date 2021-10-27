package tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Tomcat 7.0�汾: ֧��BIO, NIO
// Tomcat 8.0�汾: ֧��NIO
// Tomcat 9.0�汾(��֮ǰ): ֧��Java EE, ʵ��javax.servlet�ӿ�
// Tomcat 10.0�汾(��֮��): ֧��JakartaEE��ʵ��jakarta.servlet.Servlet�ӿ�

// Tomcat��һ��Servlet����  ==>  Ĭ�϶˿ں�8080 http://localhost:8080/
//   Servlet(�����Ӧ�ó���) = server+applet(java������Ӧ�ó���������������ˣ��ͻ���)
//   Servlet��һ���淶: �Զ���ʵ����HttpServlet������(����ʵ�ֵĽӿ�Servlet Interface)
//   Servlet��һ��С����wrapper -> Ӧ��������Context -> ������host���� -> ʹ��Engine����������

// TODO: Wrapper�����
// public class MyServlet extends HttpServlet implements SingleThreadModel {}
//   һ���Զ���ʵ����HttpServlet���͵��࣬�ڲ����ʱ�����е������ǹ���ͬһ��Servlet
//   ���ʵ����SingleThreadModel�ӿڣ����ʾÿһ��������̶߳�������ʹ��һ��Servletʵ��
//   ʹ��Wrapper����װ���е�Servletʵ��
public class TomcatBasic extends HttpServlet {

    // Tomcat����Ӧ�õ����ַ�ʽ��
    // 1. ����Ŀbuild��war�������õ�tomcat/webapps/�� ==> ��ѹ����֮��͵ڶ��ֲ���ʽ��ͬ
    // 2. ����Ŀbuild��war exploded�������ļ��з��õ�tomcat/webapps/��
    // 3. ͨ����tomcat�����ļ�server.xml��������������ʵ��
    //    <Host name="" appBase="webs">
    //       <Context path="" docBase="" />
    //    </Host>
    // 4. ��apache-tomcat-9.0.41\conf\Catalina\localhostĿ¼������ļ� ServletDemoProject.xml
    //    �ļ�������<Context path="/ServletDemoProject" docBase="ָ��build�������ļ�ȫ·��" />

    // TODO: War����jar�������� ==> �����϶��洢�˹�ͬ����Ϣ(������class�ļ���web.xml)
    // jarһ���ʾ���������İ�����tomcat����ʱ��Ҫ����������ж�
    // ʹ��war������ʽ���𣬷���tomcatʶ��ʹ���(������web����)
    // Tomcat�淶:
    //   ProjectName
    //     -- WEB-INF
    //        -- classes
    //        web.xml

    // Tomcat��������(�ַ�����)�߼�:
    // Tomcat > ApplicationFilterChain.java > ֱ�ӵ����Զ���servlet.service()
    // service()������HttpServlet��ʵ�ֵķ���  > ȷ��������������� > ����doGet()����doPost()
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
