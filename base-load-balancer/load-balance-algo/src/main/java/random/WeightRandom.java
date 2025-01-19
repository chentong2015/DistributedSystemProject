package random;

import base.ServerIP;

import java.util.Map;
import java.util.Random;

public class WeightRandom {

    // TODO. 以多大的概率随机选择指定的server
    private static String getWeightServer() {
        Random random = new Random();
        int index = random.nextInt(ServerIP.WEIGHT_SUM);

        for (Map.Entry<String, Integer> entry : ServerIP.WEIGHT_SERVERS.entrySet()) {
            int weight = entry.getValue();
            if (index < weight)
                return entry.getKey();
            index -= weight;
        }
        return "";
    }
}
