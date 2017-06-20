package com.xgene.protobuf;

import com.xgene.protobuf.OptionTickOuterClass.OptionTick;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class OptionTickServerHandler extends SimpleChannelInboundHandler<OptionTick> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx, OptionTick msg) throws Exception {
		System.out.println("from client:" + "channelRead:" + msg.getPrice());

		OptionTickOuterClass.OptionTick.Builder builder = OptionTickOuterClass.OptionTick.newBuilder();
		builder.setPrice(444);
		builder.setOptionId("OptionTick server reply");
		ctx.writeAndFlush(builder.build());
	}
}
