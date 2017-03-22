package com.ubs.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ubs.test.impl.CalculatorServiceImpl;
import com.ubs.test.impl.MarketUpdateServiceImpl;
import com.ubs.test.impl.TwoWayPriceServiceImpl;
import com.ubs.test.service.Calculator;
import com.ubs.test.service.MarketUpdate;
import com.ubs.test.service.TwoWayPrice;

@Configuration
public class AppConfig {

	@Bean
	public Calculator getCalculateService() {
		return CalculatorServiceImpl.builder().build();
	}
	
	@Bean
	public MarketUpdate getMarketUpdateService() {
		return MarketUpdateServiceImpl.builder().build();
	}
	
	@Bean
	public TwoWayPrice getTwoWayPriceServiceImpl() {
		return TwoWayPriceServiceImpl.builder().build();
	}
	
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(5);
		pool.setMaxPoolSize(10);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}

}
