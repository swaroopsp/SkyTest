package com.bjss.test.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjss.test.model.Discount;

import lombok.Builder;

@Builder
@Service
public class DiscountService {
	
	@Autowired
	private GroupOfferService groupOfferService;
	
	public List<Discount> getDisCount() {
		Discount discount1 = Discount.builder().name("Apples").discountInPercentage(BigDecimal.valueOf(10)).groupOffer(null).build();
		Discount discount2 = Discount.builder().name("Soup").discountInPercentage(BigDecimal.valueOf(0)).groupOffer(groupOfferService.getGroupOffer())
						.build();
		List<Discount> discounts = new ArrayList<>();
		discounts.add(discount1);
		discounts.add(discount2);
		return discounts;
	}
}