package com.vicp.xgene.protobuf.client;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

public class ConnectPoolManager {
	private final NioEventLoopGroup group = new NioEventLoopGroup();
	private final Bootstrap bs = new Bootstrap();
	private FixedChannelPool fixpool = null;
	private InetSocketAddress remoteaddress = null;

	public ConnectPoolManager(String host, int port, int maxconnect) {
		bs.group(group);
		bs.channel(NioSocketChannel.class);
		bs.option(ChannelOption.TCP_NODELAY, true);
		bs.option(ChannelOption.SO_KEEPALIVE, true);
		remoteaddress = InetSocketAddress.createUnresolved(host, port);
		bs.remoteAddress(remoteaddress);
		fixpool = new FixedChannelPool(bs, new DemoPoolHandler(), maxconnect);
	}

	// 申请连接，没有申请到(或者网络断开)，返回null
	public Channel acquire(int seconds) {
		try {
			Future<Channel> fch = fixpool.acquire();
			Channel ch = fch.get(seconds, TimeUnit.SECONDS);
			return ch;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 释放连接
	public void release(Channel channel) {
		try {
			if (channel != null) {
				fixpool.release(channel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		fixpool.close();
	}
}