package com.seata.template.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seata.template.model.MyTransaction;
import com.seata.template.model.TransactionType;
import com.seata.template.transactional.GlobalTransactionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext context;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive message: " + msg.toString());
        JSONObject jsonObject = JSON.parseObject((String) msg);
        String groupId = jsonObject.getString("groupId");
        String command = jsonObject.getString("command");

        // TODO: ÿһ��client���յ������ֲ�ʽ�����groupId��Ҫִ�е�command
        // �õ�GroupId��Ӧ�ķ�֧���񣬸��ݽ��յ���Server�ظ���Ϣ����������״̬
        MyTransaction transaction = GlobalTransactionManager.getMyTransaction(groupId);
        if (command.equals("commit")) {
            transaction.setTransactionType(TransactionType.commit);
        } else {
            transaction.setTransactionType(TransactionType.rollback);
        }
        // ���ѵȴ����ø÷�֧����ִ��ָ�����߼�
        transaction.getTask().signalTask();
    }

    // ��Server�˷�������
    public synchronized Object call(JSONObject data) {
        context.writeAndFlush(data.toJSONString()).channel().newPromise();
        return null;
    }
}
