package dubbo.master.ioc;

import com.alibaba.dubbo.common.URL;
import dubbo.master.api.Car;
import dubbo.master.api.Driver;

public class Trucker implements Driver {

    // �ڵ���Car�ķ���ʱ������URL���߻�ȡָ��ʵ�����ͣ�������Ե�IoCע��
    private Car car;

    // IoC�������setter����������һ�����Բ���
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public void driveCar(URL url) {
        car.getColor(url);
    }
}
