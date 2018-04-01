package com.netty.client;

import com.netty.configuration.ByteToIntegerDecoder;
import com.netty.configuration.IntegerToByteEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by maskwang on 18-3-31.
 */
public class Client {

    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 客户端辅助启动类 对客户端配置
            Bootstrap b = new Bootstrap();
            b.group(group)//
                    .channel(NioSocketChannel.class)//
                    .option(ChannelOption.TCP_NODELAY, true)//
                    .handler(new ClientChannelHandler());//
            // 异步链接服务器 同步等待链接成功
            ChannelFuture f = b.connect(host, port).sync();

            // 发送消息
            Thread.sleep(1000);
            f.channel().writeAndFlush(777);
//            f.channel().writeAndFlush(666);
//            Thread.sleep(2000);
//            f.channel().writeAndFlush(888);

            // 等待链接关闭
            f.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
            System.out.println("客户端优雅的释放了线程资源...");
        }

    }

    /**
     * 网络事件处理器
     */
    private class ClientChannelHandler extends
            ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            // 增加自定义的编码器和解码器
            ch.pipeline().addLast(new IntegerToByteEncoder());
            ch.pipeline().addLast(new ByteToIntegerDecoder());
            // 客户端的处理器
            ch.pipeline().addLast(new ClientHandler());

            ch.pipeline().addLast(new ClientHandler1());
        }

    }

    public static void main(String[] args) throws Exception {
        new Client().connect(8080, "127.0.0.1");

    }
}
