package algo.hash;

import java.util.SortedMap;

// һ����hash���ؾ����㷨
public class HashConsistency {

    // ����1:
    // ʹ��n̨���������ʱ������ĸ��ؾ����Ƕ���Դx������ʹ��hash(x)=(o mod n)ӳ�䵽ĳһ̨���������
    // �����ӻ���ٻ��������ʱ�����ܻ�ı�������Դ��Ӧ��hashֵ�����»��涼ʧЧ
    // 1. �����Ӻͼ���Serverʱ�����еķ������ܹ��ֵ��洢������Դ
    // 2. ��ÿ������ӳ�䵽Բ�����ϵ�һ���㣬ϵͳ�ٽ����õĽڵ����ӳ�䵽Բ���Ĳ�ͬλ��
    //    ��һ�¹�ϣ�㷨����õ������ӦԲ������λ�ã�����Բ�����ϲ���ֱ������ĳ���ڵ����(���󱣴�λ��)
    //    ����ɾ������ʱ�������ܱߵĶ���������ת�ƺ����䵽��Ӧ�Ļ����ϣ��������������������Ӱ��

    // ����2:
    // ���Ը��ݿͻ��˵�����ip�����hashcode������ָ����server����
    // �����ڲ�ͬ��Server��Session��һ�µ�����(û�й���)��һ���ͻ���ֻ��Ӧһ̨Server������
    // client ip --> hash function --> hashcode --> server ip
    // 1. ��ǰ����ӳ���ϵ: {hashcode:server_ip}�����ڿ����㴦���hashcode̫�࣬����޷���ǰ����
    // 2. ��ϣ��: ���ϴ洢hashcode������˳ʱ���С���󣬹涨һ����Χ��hashֵ��ӦΨһserver ip

    // ����3: �ֲ�ʽSession
    // �ڷֲ�ʽServer��Ⱥ�ܹ������У���ͬServer�������Sessionû��ȫ�ֹ���
    // �����ڿͻ������󣬾������ؾ����㷨֮���ҵ���Serverû��Session��Ϣ������޷������ز���
    
    public static String getServer(String clientInfo) {
        HashCircle hashCircle = new HashCircle();
        int hash = hashCircle.getHashcode(clientInfo);

        // �ں��������������Ѱ����������Server
        SortedMap<Integer, String> subMap = hashCircle.getTailMap(hash);
        Integer key = subMap.firstKey();

        // ���ͨ���ͻ�����Ϣ�������hashֵ����û�з���������node����ȡ��ϣ������С��node����һ����ջ�״
        if (key == null) {
            int firstKey = hashCircle.getFirstKey();
            return hashCircle.getNodeValueByKey(firstKey);
        }
        return hashCircle.getNodeValueByKey(key);
    }
}
