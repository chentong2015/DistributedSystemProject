package dubbo.master;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import dubbo.master.api.Car;
import dubbo.master.api.Driver;

import java.util.HashMap;
import java.util.Map;

// TODO: Dubbo SPI���� ==> Դ��ĺ���
// 1. ��Java SPI������ȫ��ͬ�����ܸ��Ӹ�Ч
// 2. ����IOC����ע�����: ���ʹ�õ�ʵ�����������������࣬����Զ�ע��
// 3. ����AOP����: �ܹ���ʵ����������湦��

// ͨ��SPI���ƻ�ȡЭ��: dubbo://  http://  rmi://  webservice://  redis:// ...
// ע������: Zookeeper Redis Multicast Simple
public class BaseDubboSPI {

    public static void main(String[] args) {
        // testsDubboSpiIoc();
        System.out.println("test");
    }

    private static void testDubboSpiAop() {
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);
        // ������ʵ�������л�ȡָ�����Ƶ�����
        Car car = extensionLoader.getExtension("black");
        car.getColor(null);
    }

    private static void testsDubboSpiIoc() {
        ExtensionLoader<Driver> extensionLoader = ExtensionLoader.getExtensionLoader(Driver.class);
        Driver driver = extensionLoader.getExtension("trucker");
        // ����URL����: ͨ��url�ҵ�(Ҫע���)����ʵ������(����)
        Map<String, String> map = new HashMap<>();
        map.put("carType", "black");
        URL url = new URL("", "", 8000, map);
        driver.driveCar(url);
    }

    // �����µ�����ָ��·���л�ȡ��Ϣ
    // String SERVICES_DIRECTORY = "META-INF/services/";
    // String DUBBO_DIRECTORY = "META-INF/dubbo/";
    // String DUBBO_INTERNAL_DIRECTORY = "META-INF/dubbo/internal/";

    // �������ļ��л�ȡ����Ϣ�洢��map��, ������ƿ��Զ�Ӧͬһ��ʵ������
    // private Map<String, Class<?>> getExtensionClasses() {
    //     Map<String, Class<?>> classes = (Map)this.cachedClasses.get();
    //     if (classes == null) {
    //         synchronized(this.cachedClasses) {
    //             classes = (Map)this.cachedClasses.get();
    //             if (classes == null) {
    //                 classes = this.loadExtensionClasses();
    //                 this.cachedClasses.set(classes);
    //             }
    //         }
    //     }
    //     return classes;
    // }

    // 1. ��name��Ӧ������ת��class����֤����ʱ�ӿڵ�ʵ����
    //   if (line.length() > 0) {
    //      this.loadClass(extensionClasses, resourceURL, Class.forName(line, true, classLoader), name);
    //   }
    // 2. �ж��Ƿ��Ǵ�����(ֻ����һ��) @Adaptive
    // 3. �ж��Ƿ��ǰ�װ��(�����ж��) WrapperClass
}
