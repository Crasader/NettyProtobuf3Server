package com.vicp.xgene.protobuf.client;

import com.xgene.protobuf.CustomProtobufDecoder;
import com.xgene.protobuf.CustomProtobufEncoder;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DemoPoolHandler implements ChannelPoolHandler {

	@Override
	public void channelReleased(Channel ch) throws Exception {
		System.out.println("channelReleased");
	}

	@Override
	public void channelAcquired(Channel ch) throws Exception {
		System.out.println("channelAcquired");
	}

	@Override
	public void channelCreated(Channel ch) throws Exception {
		System.out.println("channelCreated");
		NioSocketChannel channel = (NioSocketChannel) ch;
		channel.config().setKeepAlive(true);
		channel.config().setTcpNoDelay(true);
		ChannelPipeline pipeline = channel.pipeline();
		
        pipeline.addLast("decoder",new CustomProtobufDecoder());
        pipeline.addLast("encoder",new CustomProtobufEncoder());
        
		StockTickChannelHandler handler1 = new StockTickChannelHandler();
		OptionTickChannelHandler handler2 = new OptionTickChannelHandler();
		pipeline.addLast(handler1);
		pipeline.addLast(handler2);
	}
}
