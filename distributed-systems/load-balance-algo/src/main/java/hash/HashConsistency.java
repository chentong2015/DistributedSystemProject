package hash;

import java.util.SortedMap;

// һ����hash���ؾ����㷨
public class HashConsistency {

    public static String getServer(String clientInfo) {
        HashCircle hashCircle = new HashCircle();
        int hash = hashCircle.getHashcode(clientInfo);

        // �ں��������������Ѱ����������Server
        SortedMap<Integer, String> subMap = hashCircle.getTailMap(hash);
        Integer key = subMap.firstKey();

        // ��������hashֵ����û�з���������node����ȡ��ϣ������Сnode����һ����ջ�״
        if (key == null) {
            int firstKey = hashCircle.getFirstKey();
            return hashCircle.getNodeValueByKey(firstKey);
        }
        return hashCircle.getNodeValueByKey(key);
    }
}
