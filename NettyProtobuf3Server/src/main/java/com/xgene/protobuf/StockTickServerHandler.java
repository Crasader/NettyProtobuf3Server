package com.xgene.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class StockTickServerHandler extends SimpleChannelInboundHandler<StockTickOuterClass.StockTick> {
	@Override
	public void channelRead0(ChannelHandlerContext ctx, StockTickOuterClass.StockTick msg) throws Exception {
		System.out.println("from client:" + "channelRead:" + msg.getPrice());

		StockTickOuterClass.StockTick.Builder builder = StockTickOuterClass.StockTick.newBuilder();
		builder.setPrice(889);
		builder.setStockId("StockTick server replay");
		ctx.writeAndFlush(builder.build());
	}
}
