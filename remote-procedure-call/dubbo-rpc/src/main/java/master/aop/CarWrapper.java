package dubbo.master.aop;

import com.alibaba.dubbo.common.URL;
import dubbo.master.api.Car;

// AOP���棺�Խӿڵ�ʵ�����а�װ����Ҫʵ��ָ���Ľӿ�
public class CarWrapper implements Car {

    private Car car;

    // ������һ�����Բ����Ĺ�����
    // Դ��: ��ԭʼʵ�����͵�ʵ������ֵ����װ���͵�����(ͨ������������)
    // instance = this.injectExtension(wrapperClass.getConstructor(this.type).newInstance(instance)))
    // ���ջ�ʵ��Ƕ�׵İ�װ
    public CarWrapper(Car car) {
        this.car = car;
    }

    // TODO: �ڵ���Car��ʵ�����͵�ʵ���ķ���ʱ����ͨ��AOP���Ƶ��õ��÷���
    @Override
    public void getColor(URL url) {
        System.out.println("Before ...");
        car.getColor(url);
        System.out.println("After ...");
    }
}
