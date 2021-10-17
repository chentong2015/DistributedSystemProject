package dubbo.demo.protocol.http;

import dubbo.demo.framework.Invocation;
import dubbo.demo.register.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {

    // ��������(ִ�з���)�����ؽ��
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            InputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            // TODO: ���������еĶ�����з����л���ָ�����͵Ķ���
            Invocation invocation = (Invocation) objectInputStream.readObject();
            // �����յ�������ִ��ָ���ķ���
            Class classImpl = LocalRegister.get(invocation.getInterfaceName());
            // ͨ�������ָ�����������õ�ָ���ķ���
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            // ��Ҫ���ݹ������Ĳ����������ҵ�ָ���Ĺ�������Ȼ�󴴽�����
            Constructor constructor = classImpl.getConstructor();
            Object instance = constructor.newInstance();
            // ����ʵ���Ͳ�������Ҫ�ϸ���жϷ���ִ�к�Ľ����ֵ
            String result = (String) method.invoke(instance, invocation.getParamValues());

        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
