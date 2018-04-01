package client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by maskwang on 18-4-1.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {



    private int counter;
    private final String echo_req = "aaaa_#_aa_#_";


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
           throws Exception{
//        ByteBuf byteBuf = (ByteBuf)msg;
//        byte [] bytes = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(bytes);
//        String s = new String(bytes, Charset.forName("UTF-8"));
        String body = (String)msg;
        System.out.println( body+";the countor is : "+ ++counter);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        // 当客户端和服务端建立tcp成功之后，Netty的NIO线程会调用channelActive
        // 发送查询时间的指令给服务端。
        // 调用ChannelHandlerContext的writeAndFlush方法，将请求消息发送给服务端
        // 当服务端应答时，channelRead方法被调用


            ctx.writeAndFlush(Unpooled.copiedBuffer(echo_req.getBytes()));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        System.out.println("message from:"+cause.getMessage());
        ctx.close();
    }
}