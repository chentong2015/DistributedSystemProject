package algo.hash;

import model.ServerIP;

import java.util.SortedMap;
import java.util.TreeMap;

// TODO: ��ϣ����Javaʵ��: TreeMap(�ײ�����)
public class HashCircle {

    // hashcode -> server ip
    private TreeMap<Integer, String> hashCircle = new TreeMap<>();
    private static final int VIRTUAL_NODES_NUM = 160;

    // ��ÿһ����ʵ�����������㣬�������hash�㷨����ɢ��
    // ����ָ�������������㣬������ǿɢ����
    public HashCircle() {
        for (String ip : ServerIP.SERVERS) {
            for (int i = 0; i < VIRTUAL_NODES_NUM; i++) {
                int hash = getHashcode("ip" + "VN" + i);
                hashCircle.put(hash, ip);
            }
        }
    }

    public int getHashcode(String str) {
        final int p = 1677619;
        int hash = 25647578;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return (hash < 0) ? Math.abs(hash) : hash;
    }

    public int getFirstKey() {
        return hashCircle.firstKey();
    }

    public String getNodeValueByKey(int key) {
        return hashCircle.get(key);
    }

    public SortedMap<Integer, String> getTailMap(int hash) {
        return hashCircle.tailMap(hash);
    }
}
