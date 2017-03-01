package com.bjss.test.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjss.test.model.Discount;
import com.bjss.test.model.DiscountResult;
import com.bjss.test.model.Item;
import com.bjss.test.model.Result;

import lombok.Builder;

@Builder
@Service
public class BasketPricingService {
	@Autowired
	private ItemService itemService;

	@Autowired
	private DiscountService discountService;

	public Result getResult(List<String> items) {
		List<Item> items2 = itemService.getAllItems()
							.stream()
							.filter(item -> items.contains(item.getName()))
							.collect(Collectors.toList());
		return filterResult(items2);
	}

	private Result filterResult(List<Item> items) {
		List<Discount> discounts = discountService.getDisCount();
		BigDecimal subTotal = BigDecimal.ZERO ;
		List<DiscountResult> discountResults = new ArrayList<>(); 
		for(Item item : items){
			for(Discount discount : discounts){
				if(item.getName().equals(discount.getName())){
					DiscountResult discountResult = new DiscountResult();  
					discountResult.setName(item.getName());
					discountResult.setDiscountInPercentage(discount.getDiscountInPercentage());
					discountResult.setActualDiscount(item.getPrice().multiply(discount.getDiscountInPercentage()).divide(BigDecimal.valueOf(100)));
					
					discountResults.add(discountResult);
				}
			}
			subTotal = subTotal.add(item.getPrice());
		}
		
		if(Optional.of(discountResults).isPresent()){
			return Result.builder()
						.discountResults(discountResults)
						.subTotal(subTotal)
						.build();
		}else{
			return null;
		}
	}
	
	
}