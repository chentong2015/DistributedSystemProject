package dubbo.demo;

public class MyDubboFramework {

    // ��дDubbo�Ļ���ģ��
    // 1. Consumerģ��: ͨ���ӿ����ƴ�ע�����Ļ�ȡ����Ȼ����÷���
    // 2. Registryģ��: �������������Ϣ
    //    2.1 ����ע��: ������: �����ʵ��
    //    2.2 Զ��ע��: ������: List<URL>
    // 3. Providerģ��:
    //    1.1 �ṩAPI��ʵ��API����¶(������Ƕ��tomcat, nettyServer)
    //    1.2 ע������ע����񣬱���ע�����
    // 4. RcpProtocolģ�飺
    //    4.1 ����Tomcat��HttpProtocol => �����ڳ�����
    //    4.2 ����Netty��DubboProtocol => ʹ���ڲ������ȽϸߵĶ�����
}
