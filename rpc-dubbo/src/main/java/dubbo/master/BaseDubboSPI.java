package dubbo.master;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import dubbo.master.model.Car;

// TODO: Dubbo SPI���� ==> Դ��ĺ���
// 1. ��Java SPI������ȫ��ͬ�����ܸ��Ӹ�Ч
// 2. ����IOC����ע�����: ���ʹ�õ�ʵ�����������������࣬����Զ�ע��
// 3. ����AOP����: �ܹ���ʵ����������湦��
public class BaseDubboSPI {

    // dubboͨ��SPI���ƻ�ȡ��Э��:
    // dubbo://  http://  rmi://  webservice://  redis:// ...
    // ע������:
    // Zookeeper Redis Multicast Simple

    public static void main(String[] args) {
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);
        // ͨ�����ƴ����õ�ʵ�������л�ȡ
        Car car = extensionLoader.getExtension("black");
        car.getColor();
    }
}
