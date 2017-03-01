package com.bjss.test.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiscountResult {
	private String name;
	private BigDecimal actualDiscount;
	private BigDecimal discountInPercentage;
}