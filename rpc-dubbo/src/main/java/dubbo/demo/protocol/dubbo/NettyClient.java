package dubbo.demo.protocol.dubbo;

import dubbo.demo.framework.data_model.Invocation;
import dubbo.demo.protocol.base.ChannelHandlerHelper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NettyClient {

    public NettyClientHandler clientHandler;
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    // ���û�н��������ӣ����Ƚ�����Ȼ����Invocation ==> ֧�ֶ�η���
    public String send(String hostname, int port, Invocation invocation) {
        if (clientHandler == null) connectServer(hostname, port);
        clientHandler.sendInvocation(invocation);
        try {
            // ����Future<T>.get()������������ǰ�̣߳�ֱ����ȡ���񷵻ص�ֵ
            Future<String> future = executorService.submit(clientHandler);
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void connectServer(String hostname, int port) {
        clientHandler = new NettyClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(ChannelHandlerHelper.getChannelHandler(clientHandler));
            bootstrap.connect(hostname, port).sync();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
