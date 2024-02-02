package dubbo.demo.framework.protocol;

import dubbo.demo.framework.data_model.Invocation;
import dubbo.demo.framework.data_model.URL;

// ����Э���һ������ӿڣ��������������Ĳ���
public interface Protocol {

    // �������Э������Ӧ��Server
    void startServer(URL url);

    // �ͻ���ʹ��ָ����Э�����Զ��ָ���ķ���(ͨ��Invocation��װ��Ϣ)
    String sendInvocation(URL url, Invocation invocation);
}
