package com.vicp.xgene.protobuf.client;

import com.xgene.protobuf.OptionTickOuterClass;
import com.xgene.protobuf.OptionTickOuterClass.OptionTick;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


@Sharable
public class OptionTickChannelHandler extends SimpleChannelInboundHandler<OptionTick> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, OptionTick msg) throws Exception {
		System.out.println("from server:" + "channelRead:" + msg.getPrice());

		OptionTickOuterClass.OptionTick.Builder builder = OptionTickOuterClass.OptionTick.newBuilder();
		builder.setPrice(444);
		builder.setOptionId("OptionTick client reply");
		ctx.writeAndFlush(builder.build());
	}

}