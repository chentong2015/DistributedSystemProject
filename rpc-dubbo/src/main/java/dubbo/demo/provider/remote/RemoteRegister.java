package dubbo.demo.provider.remote;

import dubbo.demo.framework.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ע�ᵽԶ�˵�ע������
public class RemoteRegister {

    private static Map<String, List<URL>> map = new HashMap<>();

    // ��URL��ӵ��ӿ����ƶ�Ӧ��list��
    public static void register(String interfaceName, URL url) {
        if (map.containsKey(interfaceName)) {
            map.get(interfaceName).add(url);
        } else {
            List<URL> list = new ArrayList<>();
            list.add(url);
            map.put(interfaceName, list);
        }
    }
}
