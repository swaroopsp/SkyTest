package com.bjss.test.model;

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
public class GroupOffer {
	private String item;
	private int quantity;
	private String relatedItem;
	private double discountInPercentage;
}
