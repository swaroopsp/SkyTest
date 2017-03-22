package com.ubs.test.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ubs.test.constant.Instrument;
import com.ubs.test.constant.State;
import com.ubs.test.service.TwoWayPrice;

import lombok.Builder;

@Service
@Builder
@Scope("prototype")
public class TwoWayPriceServiceImpl implements TwoWayPrice {

	public Instrument getInstrument() {
		return null;
	}

	public State getState() {
		return null;
	}

	public double getBidPrice() {
		return 0;
	}

	public double getOfferAmount() {
		return 0;
	}

	public double getOfferPrice() {
		return 0;
	}

	public double getBidAmount() {
		return 0;
	}

}
