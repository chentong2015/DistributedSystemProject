package dubbo.demo.protocol.base;

import dubbo.demo.framework.data_model.Invocation;
import dubbo.demo.register.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvocationHelper {

    // TODO: ���������еĶ�����з����л���ָ�����͵Ķ���
    public static Invocation parseInvocationObjectFormHttpServlet(HttpServletRequest req) {
        try {
            InputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (Invocation) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return new Invocation("");
    }

    // �����յ�������ִ��ָ���ķ���
    public static String getInvocationResult(Invocation invocation) {
        try {
            Class classImpl = LocalRegister.get(invocation.getInterfaceName());
            // ͨ�������ָ�����������õ�ָ���ķ���
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            // ��Ҫ���ݹ������Ĳ����������ҵ�ָ���Ĺ�������Ȼ�󴴽�����
            Constructor constructor = classImpl.getConstructor();
            Object instance = constructor.newInstance();
            // ����ʵ���Ͳ�������Ҫ�ϸ���жϷ���ִ�к�Ľ����ֵ
            return (String) method.invoke(instance, invocation.getParamValues());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
