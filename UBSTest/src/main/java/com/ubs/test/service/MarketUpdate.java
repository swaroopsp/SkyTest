package com.ubs.test.service;

import com.ubs.test.constant.Market;

public interface MarketUpdate {
    Market getMarket();
    TwoWayPrice getTwoWayPrice(Market market);
}
