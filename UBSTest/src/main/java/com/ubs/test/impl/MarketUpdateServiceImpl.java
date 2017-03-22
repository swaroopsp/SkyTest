package com.ubs.test.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ubs.test.constant.Market;
import com.ubs.test.service.MarketUpdate;
import com.ubs.test.service.TwoWayPrice;

import lombok.Builder;

@Service
@Builder
@Scope("prototype")
public class MarketUpdateServiceImpl implements MarketUpdate{
	private final ReentrantLock lock = new ReentrantLock();
	private int count = 0;
	
	public Market getMarket() {
		List<Market> markets = null;	
		lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " gets Count: " + count);
            markets = Arrays.asList(Market.values());
            Collections.sort(markets, Market.comparator);
            return markets.get(0);
        } finally {
            lock.unlock();            
        }
	}

	public TwoWayPrice getTwoWayPrice(Market market) {
		lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " gets Count: " + count);
            return null;//Returns null. Implementation required 
        } finally {
            lock.unlock();
        }
	}
}