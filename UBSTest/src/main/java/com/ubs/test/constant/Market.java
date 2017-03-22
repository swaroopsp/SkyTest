package com.ubs.test.constant;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;

/**
 * 
 * @author swaroop Assuming these values are streaming live
 */
public enum Market {
	MARKET0(LocalDateTime.of(2016, Month.DECEMBER, 31, 23, 57)), 
	MARKET1(LocalDateTime.of(2016, Month.DECEMBER, 31, 23, 59)),
	MARKET49(LocalDateTime.of(2016, Month.DECEMBER, 31, 23, 56));

	private final LocalDateTime localDateTime;

	private Market(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public static Comparator<Market> comparator = new Comparator<Market>() {
		public int compare(Market market1, Market market2) {
			return market1.localDateTime.compareTo(market2.localDateTime);
		}
	};
}
