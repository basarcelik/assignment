package com.caocity.baraka.assignment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class Config {

	@Value("${org.caocity.baraka.candle.interval}")
	public long interval;

}
