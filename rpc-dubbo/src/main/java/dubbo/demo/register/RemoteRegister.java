package dubbo.demo.register;

import dubbo.demo.model.URL;

import java.io.*;
import java.util.*;

// ע�ᵽԶ�˵�ע������
// TODO: ע��һ̨����������������: һ������ע�����Ϣû�а취�������һ������ʹ��
// 1. ���ط�ʽ: ʹ���ļ���������Ϣ�־û������̣�Ȼ���ȡ
// 2. Զ�˷�ʽ: Dubboʹ�÷ֲ�ʽ�м��(redis, zk)�����"ע������"�洢����
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
        saveFile(); // �־û��������ļ���
    }

    // ģ���Զ��ע�������õ������б�
    // ģ��ʹ��"���"���ؾ�����ԣ����б���ȡ��һ��URL�����÷���
    public static URL getRandomURL(String interfaceName) {
        List<URL> list = getFile().get(interfaceName);
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    // ����Ĭ��д���·����D��
    public static void saveFile() {
        try {
            FileOutputStream outputStream = new FileOutputStream("/RemoteRegister.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/RemoteRegister.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
