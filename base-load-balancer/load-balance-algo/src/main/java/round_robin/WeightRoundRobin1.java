package round_robin;

import base.RequestId;
import base.ServerIP;
import java.util.Map;

// TODO. ��Ȩ��ѯ�����ݲ�ͬ��Ȩ�ط��䲻ͬ����������
public class WeightRoundRobin1 {

    private static String getWeightServer() {
        int requestId = RequestId.getRequestId();
        int index = requestId % ServerIP.WEIGHT_SUM;
        for (Map.Entry<String, Integer> entry : ServerIP.WEIGHT_SERVERS.entrySet()) {
            int weight = entry.getValue();
            if (index < weight) {
                return entry.getKey();
            }

            // ͨ�����ϼ���Ȩ�أ���ֵ�������䵽�ض���Server
            index -= weight;
        }
        return "No Server Found";
    }
}
