package com.rpc.server;

import com.rpc.server.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainServer {

    // RPC����˶��Ⱪ¶socket�ӿڣ��������������������
    // ʹ���첽�ķ�ʽ���������󣬱���RPC������ڽ�������ʱ������
    public static void main(String[] args) throws IOException {
       try(ServerSocket serverSocket = new ServerSocket(1234)){
           Executor executor = Executors.newFixedThreadPool(10);
           while (true) {
               System.out.println("wait connection");
               Socket socket = serverSocket.accept();
               System.out.println("connection ok");
               // ִ��ͨѶ�ɹ���socket
               executor.execute(new RequestHandler(socket));
           }
       }
    }
}
