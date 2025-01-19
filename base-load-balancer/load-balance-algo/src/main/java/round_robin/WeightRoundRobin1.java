package round_robin;

import base.RequestId;
import base.ServerIP;
import java.util.Map;

// TODO. 加权轮询，根据不同的权重分配不同的请求数量
public class WeightRoundRobin1 {

    private static String getWeightServer() {
        int requestId = RequestId.getRequestId();
        int index = requestId % ServerIP.WEIGHT_SUM;
        for (Map.Entry<String, Integer> entry : ServerIP.WEIGHT_SERVERS.entrySet()) {
            int weight = entry.getValue();
            if (index < weight) {
                return entry.getKey();
            }

            // 通过不断减少权重，将值按比例落到特定的Server
            index -= weight;
        }
        return "No Server Found";
    }
}
