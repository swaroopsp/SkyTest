package com.ubs.test.service;

import com.ubs.test.constant.Instrument;
import com.ubs.test.constant.State;

public interface TwoWayPrice {
    Instrument getInstrument();
    State getState();
    double getBidPrice();
    double getOfferAmount();
    double getOfferPrice();
    double getBidAmount();
}