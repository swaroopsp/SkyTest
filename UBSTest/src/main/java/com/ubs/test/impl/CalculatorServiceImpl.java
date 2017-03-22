package com.ubs.test.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ubs.test.constant.Market;
import com.ubs.test.service.Calculator;
import com.ubs.test.service.MarketUpdate;
import com.ubs.test.service.TwoWayPrice;

import lombok.Builder;

@Service
@Builder
@Scope("prototype")
public class CalculatorServiceImpl implements Calculator{
	public TwoWayPrice applyMarketUpdate(MarketUpdate marketUpdate) {
		Market market = marketUpdate.getMarket();
		return marketUpdate.getTwoWayPrice(market);
	}
}