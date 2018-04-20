package com.springboot.D12_Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: yinchengjian
 * @Description:
 * @Date: 2018/4/19
 * @Modified By:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String message = new String(bytes, "UTF-8");
        System.out.println("服务端收到的消息： " + message);

        //向客户端写数据
        String response = "hello client";
        ByteBuf buffer = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(buffer);//写入缓冲数组
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete...");
        ctx.flush();//将缓冲区数据写入SocketChannel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常
        cause.printStackTrace();
        System.out.println("exceptionCaught...");
    }





}