package dubbo.demo.protocol.http;

import dubbo.demo.framework.data_model.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

// �Զ���ģ��HttpClient��ʵ��
public class HttpClient {

    public String send(String hostname, int port, Invocation invocation) {
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // д����������������ݵ�HttpServer(Tomcat)
            OutputStream outputStream = connection.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            // TODO: ������������л���Ȼ��ͨ��������д���
            objectOutputStream.writeObject(invocation);
            objectOutputStream.flush();
            objectOutputStream.close(); // ����Ӧ�ñ�д��finally��

            // �������󣬲����õ�Server��ִ�з����Ľ��
            InputStream inputStream = connection.getInputStream();
            return IOUtils.toString(inputStream, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
