package com.codeanalysis.netty.helloworld.client;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * netty客户端入门
 *
 * @author -琴兽-
 */
public class Client {

    public static void main(String[] args) {

        //服务类
        ClientBootstrap bootstrap = new ClientBootstrap();

        //线程池-----------便于分析查看，只搞一线程
        ExecutorService boss = Executors.newFixedThreadPool(1);
        ExecutorService worker = Executors.newFixedThreadPool(1);

        //socket工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));

        //管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("hiHandler", new HiHandler());
                return pipeline;
            }
        });

        //连接服务端
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
        Channel channel = connect.getChannel();

        System.out.println("client start");

//		Scanner scanner = new Scanner(System.in);
//		while(true){
//			System.out.println("请输入");
//			channel.write(scanner.next());
//		}
        int i = 0;
        while (i < 4) {
            i++;
            channel.write("我是[客户端，" + i + "号小姐]，你是谁？");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bootstrap.shutdown();
        boss.shutdown();
        worker.shutdown();
    }

}
