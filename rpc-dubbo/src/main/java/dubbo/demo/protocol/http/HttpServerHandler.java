package dubbo.demo.protocol.http;

import dubbo.demo.framework.data_model.Invocation;
import dubbo.demo.protocol.base.InvocationHelper;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class HttpServerHandler {

    // ��������(ִ�з���)�����ؽ��
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Invocation invocation = InvocationHelper.parseInvocationObjectFormHttpServlet(req);
            String result = InvocationHelper.getInvocationResult(invocation);
            // ��ִ�з����Ľ������
            IOUtils.write(result, resp.getOutputStream(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
