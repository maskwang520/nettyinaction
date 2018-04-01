package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by maskwang on 18-4-1.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter=0;

    // 用于网络的读写操作
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception{

        String body = (String)msg;
        System.out.println(body+";the counter is :"+ (++counter));

        ByteBuf resp = Unpooled.copiedBuffer(("hello"+"_#_").getBytes());
        ctx.writeAndFlush(resp);

        // 当客户端和服务端建立tcp成功之后，Netty的NIO线程会调用channelActive
        // 发送查询时间的指令给服务端。
        // 调用ChannelHandlerContext的writeAndFlush方法，将请求消息发送给服务端
        // 当服务端应答时，channelRead方法被调用
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
