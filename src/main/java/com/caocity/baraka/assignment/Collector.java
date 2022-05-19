package com.caocity.baraka.assignment;

import java.net.URI;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.caocity.baraka.assignment.model.Candle;
import com.caocity.baraka.assignment.model.Data;
import com.caocity.baraka.assignment.model.Stock;
import com.google.gson.Gson;



@Service
public class Collector implements Runnable{
	
	Logger logger = LoggerFactory.getLogger(Collector.class);
	
	public long interval;
	
	private CacheController cacheController;
	
	private Config config;
	
	private Candle updateCandle(Stock stock,Candle candle) {
		candle.setClose(stock.getP());
		if(candle.getHigh()<stock.getP())candle.setHigh(stock.getP());
		if(candle.getLow()>stock.getP())candle.setLow(stock.getP());
		return candle;
	}
	private Candle initCandle(Stock stock,long period) {
		return new Candle(stock,period*interval);
	}
	@Override
	public void run() {
		try {
			Thread.sleep(10000);
			this.cacheController=ContextUtilExt.getBean(CacheController.class);
			this.config=ContextUtilExt.getBean(Config.class);
			this.interval=config.interval;
			final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://b-mocks.dev.app.getbaraka.com:9989"));
            while(clientEndPoint.isWorking()||!clientEndPoint.isOpened()) {
	            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
	                public void handleMessage(String message) {
	                	Gson g = new Gson();  
	                	Data data = g.fromJson(message, Data.class);  
	                	Stock stock=data.getData()[0];
	                	long period=stock.getT()/interval;
	                	ArrayList<Candle> candleVal=cacheController.getCandleVals(stock.getS());
	                	if(candleVal==null) {
	                		candleVal=new ArrayList<Candle>();
	                		candleVal.add(initCandle(stock,period));
	                	}else {
	                		int index=candleVal.size()-1;
	                		if(candleVal.get(index).getTime()/interval!=period) {
	                			candleVal.add(initCandle(stock,period));
	                		}else {
	                			Candle candle=updateCandle(stock,candleVal.get(index));
	                			candleVal.remove(index);
	                			candleVal.add(candle);
	                		}
	                	}
	                	cacheController.setCandleVals(candleVal, stock.getS());
	                	//logger.info(message);
	                }
	            });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
