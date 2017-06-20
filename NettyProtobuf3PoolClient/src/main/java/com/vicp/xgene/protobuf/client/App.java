package com.vicp.xgene.protobuf.client;

import com.xgene.protobuf.OptionTickOuterClass;
import com.xgene.protobuf.StockTickOuterClass;

import io.netty.channel.Channel;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			int maxconnect = 10;
			ConnectPoolManager pool = new ConnectPoolManager("127.0.0.1", 8888, maxconnect);
			Channel ch = pool.acquire(5);
			
//			StockTickOuterClass.StockTick.Builder builder = StockTickOuterClass.StockTick.newBuilder();
//			builder.setPrice(889);
//			builder.setStockId("StockTick client init send");

			OptionTickOuterClass.OptionTick.Builder builder = OptionTickOuterClass.OptionTick.newBuilder();
			builder.setPrice(444);
			builder.setOptionId("OptionTick client init send");
			
			ch.writeAndFlush(builder.build());
			
			pool.release(ch);

//			Channel[] chlist = new Channel[maxconnect];
//			for (int i = 0; i < maxconnect; i++) {
//				chlist[i] = pool.acquire(1);
//			}
//			for (int i = 0; i < maxconnect; i++) {
//				pool.release(chlist[i]);
//			}
			
			System.out.println("StockTick client init send");
			
			pool.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
