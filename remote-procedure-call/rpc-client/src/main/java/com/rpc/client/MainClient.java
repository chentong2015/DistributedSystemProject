package com.rpc.client;

import com.rpc.client.model.RpcInfo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainClient {

    // �ͻ���ͨ��Server�˱�¶��Socket�ӿ�������ͨѶ��ִ��ָ��API�ĵ���
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 1234);

        // ʵս��Ŀ����Ҫͨ��"������"���ж�Ҫ���õķ���
        RpcInfo rpcInfo = new RpcInfo();
        rpcInfo.setPackageName("com.rpc.server.service");
        rpcInfo.setClazzName("ServerDao");
        rpcInfo.setMethodName("print");
        rpcInfo.setArgs(new Object[]{"tong"});

        // ��Ҫ��Server�˵Ľ��̷��ͷ�װ�Ķ�����
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(rpcInfo);
        System.out.println("Send RPC request done!");
    }
}
