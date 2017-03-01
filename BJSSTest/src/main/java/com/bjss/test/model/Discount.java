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
public class Discount {
	private String name;
	private BigDecimal discountInPercentage;
	private GroupOffer groupOffer;
	private BigDecimal discountedPrice;
	
}
