package dubbo.demo.protocol.http;

import dubbo.demo.framework.Invocation;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
