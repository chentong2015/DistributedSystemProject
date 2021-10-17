package dubbo.demo.protocol.http;

import dubbo.demo.model.Invocation;
import dubbo.demo.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class HttpServerHandler {

    // ��������(ִ�з���)�����ؽ��
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Invocation invocation = parseInvocationObject(req);
            String result = getInvocationResult(invocation);
            // ��ִ�з����Ľ������
            IOUtils.write(result, resp.getOutputStream(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: ���������еĶ�����з����л���ָ�����͵Ķ���
    private Invocation parseInvocationObject(HttpServletRequest req) {
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
    private String getInvocationResult(Invocation invocation) {
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
