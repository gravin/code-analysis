package com.codeanalysis.netty.ch3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.TimeUnit;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("渠道激活");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("渠道注册");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("handler增加");
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到消息:");
        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.print("message received: ");
            while(in.isReadable()){
                System.out.print((char)in.readByte());
                System.out.flush();
            }
        }finally{
            ReferenceCountUtil.release(msg); // (2)
        }
        ctx.close();
//        super.channelRead(ctx, msg);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // 耗时的操作
//                String result = loadFromDB();
//
//                ctx.channel().writeAndFlush(result);
//                ctx.executor().schedule(new Runnable() {
//                    @Override
//                    public void run() {
//                        // ...
//                    }
//                }, 1, TimeUnit.SECONDS);
//
//            }
//        }).start();
    }

    private String loadFromDB() {
        return "hello world!";
    }
}
