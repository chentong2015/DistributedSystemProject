package com.rpc.server.request;

import com.rpc.client.model.RpcInfo;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class RequestHandler implements Runnable {

    // ��������ʱ��Ҫ��Socket�л�ȡ���������Ϣ
    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            RpcInfo rpcInfo = (RpcInfo) objectInputStream.readObject();
            invokeMethod(rpcInfo);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // ͨ�������ȡ��Ҫ���õķ������Ϣ�����÷���
    private void invokeMethod(RpcInfo rpcInfo) throws Exception {
        String classFullPath = rpcInfo.getPackageName() + "." + rpcInfo.getClazzName();
        Class cls = Class.forName(classFullPath);
        Class[] parameterTypes = new Class[rpcInfo.getArgs().length];
        for (int index = 0; index < rpcInfo.getArgs().length; index++) {
            parameterTypes[index] = rpcInfo.getArgs()[index].getClass();
        }

        Method method = cls.getMethod(rpcInfo.getMethodName(), parameterTypes);
        method.invoke(cls.newInstance(), rpcInfo.getArgs());
    }
}
