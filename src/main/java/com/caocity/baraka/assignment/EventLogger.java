package com.caocity.baraka.assignment;


import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventLogger implements CacheEventListener<Object, Object> {
	Logger logger = LoggerFactory.getLogger(EventLogger.class);
	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
		logger.info(event.getType().name() , event.getKey(), event.getOldValue(), event.getNewValue());
		
	}
}
