package com.netty.configuration;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by maskwang on 18-3-31.
 */
public class IntegerToByteEncoder  extends MessageToByteEncoder<Integer>{

    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) throws Exception {


        System.out.println("encode message is msg"+msg);
        out.writeInt(msg);
    }
}
