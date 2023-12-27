package algo.random;

import model.ServerIP;

import java.util.Map;
import java.util.Random;

// ����㷨
// 0. �����㷨
// 1. ������server����Ȩ��������ȵķ����������༯�������һ���� ==> ����Ȩ�ؽ��и��ƣ��ڴ�Ч�ʵ�
// 2. ��Ȩ��ת����x�����᳤�ȣ�ֵԽ�����������䷶ΧԽ�����ֵ�䵽����ĸ���Խ�� ==> ��Server�����޹�
public class WeightRandom {

    // �Զ��ĸ���ѡ��ָ����server
    // �Ż�: ���ж��Ƿ����õ�Ȩ�ض���һ���ģ�����Ҫ�ڼ�Ȩ��
    private static String getWeightServer() {
        Random random = new Random();
        int index = random.nextInt(ServerIP.WEIGHT_SUM);
        for (Map.Entry<String, Integer> entry : ServerIP.WEIGHT_SERVERS.entrySet()) {
            if (index < entry.getValue())
                return entry.getKey();
            index -= entry.getValue(); // ��ȥ֮ǰ�����������
        }
        return "";
    }
}
