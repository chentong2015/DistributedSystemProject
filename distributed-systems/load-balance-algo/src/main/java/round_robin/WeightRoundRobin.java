package round_robin;

import model.RequestId;
import model.ServerIP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ������ѯ
// 0. �����㷨
// 1. ����Server��Ȩ����������ļ��ϣ������༯���н�����ѯ
// 2. ������ѯ��indexλ��ȷ�����ڵ����䷶Χ��Ȼ��index++
// 3. ƽ����Ȩ��ѯ
public class WeightRoundRobin {

    private static String getWeightServer() {
        int requestId = RequestId.getRequestId();
        int index = requestId % ServerIP.WEIGHT_SUM;
        for (Map.Entry<String, Integer> entry : ServerIP.WEIGHT_SERVERS.entrySet()) {
            if (index < entry.getValue())
                return entry.getKey();
            index -= entry.getValue();
        }
        return "";
    }

    // TODO: ƽ����Ȩ��ѯ ==> ��ѧ��֤(��ν���Ȩ����һ�ֹ�����ƽ̹����Ȩ�صķ�����)
    // �Ż�������Ȩ�����ù����server�����һֱ��������Ȩ��С�ķ�����û�л��ᴦ������
    private static int sumWeight = 0;
    private static List<Integer> weights = new ArrayList<>();
    private static List<Integer> currentWeights = new ArrayList<>();
    private static Map<Integer, String> mapServers = new HashMap<>(); // �Կռ任ʱ��

    public static void main(String[] args) {
        loadAllServerWeights();
        System.out.println(getWeightServer2());
        System.out.println(getWeightServer2());
    }

    // ֻ����һ������
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

    // ����һ�������ֵ��ȷ��Server, ���indexλ��
    private static String getWeightServer2() {
        int maxWeight = 0;
        int maxWeightIndex = 0;
        for (int index = 0; index < weights.size(); index++) {
            int serverWeight = weights.get(index);
            int newCurrentWeight = currentWeights.get(index) + serverWeight;
            currentWeights.set(index, newCurrentWeight);
            // ��ѭ���Ĺ�����ͬʱȷ�����ֵ
            if (newCurrentWeight > maxWeight) {
                maxWeight = newCurrentWeight;
                maxWeightIndex = index;
            }
        }
        // ֻ�ı����Ȩ��λ�õ�ֵ, �ֵ�Ȩ��
        int newMaxWeightValue = currentWeights.get(maxWeightIndex) - sumWeight;
        currentWeights.set(maxWeightIndex, newMaxWeightValue);
        return mapServers.get(maxWeightIndex);
    }

    // TODO: ƽ����Ȩ��ѯ ==> ��������һ��ʵ��, ��װ��ָ����Class����
    // ʹ��server IP��ΪkeyΨһ
    private static Map<String, Weight> weightMap = new HashMap<>();

    public static String getWeightServer3() {
        int totalWeight = 7; // ������Ҫͨ��map������ͳ��
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
        // ���ҵ�ӵ�����currentWeightȨ�ص��Ǹ�����ˢ��ֵ
        maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight() - totalWeight);
        return maxCurrentWeight.getIp();
    }
}
