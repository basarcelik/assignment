package com.caocity.baraka.assignment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;

import com.caocity.baraka.assignment.model.Candle;

@Service
public class CacheController {
	@Autowired
	CacheManager cacheManager;
	
	public void setCandleVals(ArrayList<Candle> candleVals,String stockName) {
		if(cacheManager==null)return;
		Cache cache=cacheManager.getCache("candles");
		cache.put(stockName, candleVals);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Candle> getCandleVals(String stockName){
		if(cacheManager==null)return null;
		Cache cache=cacheManager.getCache("candles");
		SimpleValueWrapper wrapper=(SimpleValueWrapper)cache.get(stockName);
		if(wrapper!=null) return (ArrayList<Candle>)wrapper.get(); 
		return null;
	}
}
