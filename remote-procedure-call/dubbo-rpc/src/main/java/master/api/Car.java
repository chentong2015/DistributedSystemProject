package dubbo.master.api;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

// ֤���ýӿ��ǿ��Ա���չ��
// ����ָ��Ĭ�ϵ�ʵ������
@SPI(value = "red")
public interface Car {

    // ͨ��URL���������õ���Ϣ��ȷ��ʹ�þ���ʵ�ֵ�����
    @Adaptive(value = "carType")
    void getColor(URL url);
}
