package com.caocity.baraka.assignment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caocity.baraka.assignment.model.Candle;

@RestController
public class Controller {
	
	@Autowired CacheController cacheController;

	@RequestMapping(value = "/candles/{symbol}", method = RequestMethod.GET)
	public List<Candle> res(@PathVariable String symbol) throws Exception {
		
		ArrayList<Candle> candleVals = cacheController.getCandleVals(symbol);
		
		if(candleVals==null)candleVals = new ArrayList<Candle>();
		
		return candleVals;
		
	}

}