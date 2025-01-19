package round_robin;

import base.ServerIP;

import java.util.HashMap;
import java.util.Map;

// TODO: 平滑加权轮询 ==> 面向对象的一种实现, 封装成指定的Class对象
public class WeightRoundRobin3 {

    // 使用server IP作为key唯一
    private static Map<String, Weight> weightMap = new HashMap<>();

    public static String getWeightServer() {
        int totalWeight = 7; // 这里需要通过map来进行统计
        if (weightMap.isEmpty()) {
            ServerIP.WEIGHT_SERVERS.forEach((ip, weight) -> {
                weightMap.put(ip, new Weight(ip, weight, 0));
            });
        }

        Weight maxCurrentWeight = null;
        for (Weight weight : weightMap.values()) {
            weight.setCurrentWeight(weight.getCurrentWeight() + weight.getWeight());
            if (maxCurrentWeight == null) {
                maxCurrentWeight = weight;
            } else if (maxCurrentWeight.getCurrentWeight() < weight.getCurrentWeight()) {
                maxCurrentWeight = weight;
            }
        }

        // 对找的拥有最多currentWeight权重的那个对象，刷新值
        maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight() - totalWeight);
        return maxCurrentWeight.getIp();
    }

    static class Weight {

        private String ip;
        private int weight;
        private int currentWeight;

        public Weight(String ip, int weight, int currentWeight) {
            this.ip = ip;
            this.weight = weight;
            this.currentWeight = currentWeight;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getCurrentWeight() {
            return currentWeight;
        }

        public void setCurrentWeight(int currentWeight) {
            this.currentWeight = currentWeight;
        }
    }
}
