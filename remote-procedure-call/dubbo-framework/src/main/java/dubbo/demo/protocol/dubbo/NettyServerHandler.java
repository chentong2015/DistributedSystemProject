package dubbo.demo.protocol.dubbo;

import dubbo.demo.framework.data_model.Invocation;
import dubbo.demo.protocol.base.InvocationHelper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Connected one client");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ���������˽�����������ֱ��ǿ��ת������
        Invocation invocation = (Invocation) msg;
        System.out.println("get: " + invocation.getInterfaceName());
        Object result = InvocationHelper.getInvocationResult(invocation);
        // �����д�ص��ͻ���
        ctx.writeAndFlush(result);
    }
}
