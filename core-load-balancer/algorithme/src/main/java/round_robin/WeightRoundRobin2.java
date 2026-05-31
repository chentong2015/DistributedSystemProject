package round_robin;

import base.ServerIP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: 平滑加权轮询 ==> 数学论证(如何将高权重在一轮过程中平坦给低权重的服务器)
// 优化：对于权重配置过大的server，则会一直处理请求，权重小的服务器没有机会处理请求
public class WeightRoundRobin2 {

    private static int sumWeight = 0;
    private static List<Integer> weights = new ArrayList<>();
    private static List<Integer> currentWeights = new ArrayList<>();
    private static Map<Integer, String> mapServers = new HashMap<>(); // 以空间换时间

    // 只加载一次数据
    private static void loadAllServerWeights() {
        int index = 0;
        for (Map.Entry<String, Integer> entry : ServerIP.WEIGHT_SERVERS.entrySet()) {
            sumWeight += entry.getValue();
            weights.add(entry.getValue());
            currentWeights.add(0);
            mapServers.put(index, entry.getKey());
            index++;
        }
    }

    // 根据一轮中最大值来确定Server, 标记index位置
    private static String getWeightServer() {
        int maxWeight = 0;
        int maxWeightIndex = 0;
        for (int index = 0; index < weights.size(); index++) {
            int serverWeight = weights.get(index);
            int newCurrentWeight = currentWeights.get(index) + serverWeight;
            currentWeights.set(index, newCurrentWeight);
            // 在循环的过程中同时确定最大值
            if (newCurrentWeight > maxWeight) {
                maxWeight = newCurrentWeight;
                maxWeightIndex = index;
            }
        }
        // 只改变最大权重位置的值, 分担权重
        int newMaxWeightValue = currentWeights.get(maxWeightIndex) - sumWeight;
        currentWeights.set(maxWeightIndex, newMaxWeightValue);
        return mapServers.get(maxWeightIndex);
    }
}
