package dubbo.demo.model;

import java.io.Serializable;

// schema://hostname:port
// ���������URL��Ϣ�ᱻ�־û������أ���Ҫд��IO�ļ����������Ҫ����������л�
public class URL implements Serializable {

    private String hostname;
    private int port;

    public URL(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
