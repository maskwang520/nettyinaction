package com.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by maskwang on 18-3-31.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            Integer body = (Integer) msg;
            System.out.println("Client :" + body.toString());

            // 只是读数据，没有写数据的话
            // 需要自己手动的释放的消息
            ctx.fireChannelRead(msg);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }
}
