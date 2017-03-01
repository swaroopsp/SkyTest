package com.bjss.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bjss.test.service.BasketPricingService;
import com.bjss.test.service.DiscountService;
import com.bjss.test.service.GroupOfferService;
import com.bjss.test.service.ItemService;

@Configuration
public class AppConfig {

	@Bean
	public BasketPricingService getBasketPricingService() {
		return BasketPricingService.builder().build();
	}

	@Bean
	public DiscountService getDiscountService() {
		return DiscountService.builder().build();
	}

	@Bean
	public GroupOfferService getGroupOfferService() {
		return GroupOfferService.builder().build();
	}

	@Bean
	public ItemService getItemService() {
		return ItemService.builder().build();
	}
}
