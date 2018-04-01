package com.netty.configuration;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by maskwang on 18-3-31.
 */
public class ByteToIntegerDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {


        if(in.readableBytes()>=4){
            int n = in.readInt();
            System.out.println("decode meg is "+n);
            out.add(n);
        }
    }
}
