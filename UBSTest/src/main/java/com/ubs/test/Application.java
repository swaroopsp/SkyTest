package com.ubs.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ubs.test.service.Calculator;
import com.ubs.test.service.MarketUpdate;
import com.ubs.test.service.TwoWayPrice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("resource")
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		runThreadUsingConcurrency();		
	}
	
	public static TwoWayPrice runThreadUsingConcurrency(){
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Calculator calculator = context.getBean(Calculator.class);
		MarketUpdate marketUpdate = context.getBean(MarketUpdate.class);
		
//		ExecutorService executor = Executors.newFixedThreadPool(2);
		ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
		Callable<TwoWayPrice> callable = new Callable<TwoWayPrice>() {
	        @Override
	        public TwoWayPrice call() {
	        	return calculator.applyMarketUpdate(marketUpdate);
	        }
	    };
	    Future<TwoWayPrice> future = executor.submit(callable);
	    
	    TwoWayPrice twoWayPrice = null;
	    try {
	    	twoWayPrice = future.get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Unexpected error occured - " + e.getMessage());
			e.printStackTrace();
		}finally{
			executor.shutdown();
		}
	    return twoWayPrice;
	}

}
