package com.vicp.xgene.protobuf.client;

import com.xgene.protobuf.StockTickOuterClass;
import com.xgene.protobuf.StockTickOuterClass.StockTick;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


@Sharable
public class StockTickChannelHandler extends SimpleChannelInboundHandler<StockTickOuterClass.StockTick> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, StockTick msg) throws Exception {
		System.out.println("from server:" + "channelRead:" + msg.getPrice());

		StockTickOuterClass.StockTick.Builder builder = StockTickOuterClass.StockTick.newBuilder();
		builder.setPrice(889);
		builder.setStockId("StockTick client replay");
		ctx.writeAndFlush(builder.build());
	}

}