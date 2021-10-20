package dubbo.demo.framework.factory;

import dubbo.demo.framework.protocol.DubboProtocol;
import dubbo.demo.framework.protocol.HttpProtocol;
import dubbo.demo.framework.protocol.Protocol;

public class ProtocolFactory {

    // ʹ��JVM��׼����(-DprotocolName="xxx")������ʹ������Э��
    // 1. ֻ��ҪΨһ�޸����л�
    // 2. ��������Э�飬����Ҫ�޸Ĺ�����
    public static Protocol getProtocol() {
        String protocolName = "dubbo"; // System.getProperty("protocolName");
        if (protocolName == null || protocolName.equals("") || protocolName.equals("http")) {
            return new HttpProtocol();
        }
        return new DubboProtocol();
    }
}
