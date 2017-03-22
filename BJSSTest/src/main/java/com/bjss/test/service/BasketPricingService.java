package com.bjss.test.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bjss.test.model.Discount;
import com.bjss.test.model.DiscountResult;
import com.bjss.test.model.Item;
import com.bjss.test.model.Result;
import com.bjss.test.service.DiscountService;
import com.bjss.test.service.ItemService;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Builder
@Service
@Slf4j
public class BasketPricingService {
	@Autowired
	private ItemService itemService;

	@Autowired
	private DiscountService discountService;

	public Result getResult(String input) {
		if (StringUtils.isEmpty(input)) {
			log.error("Input can not be empty");
			throw new IllegalArgumentException("Input can not be empty");
		}

		List<String> elements = getAllElements(input);

		List<String> items = elements.subList(1, elements.size());
		if (items.size() == 0) {
			log.error("No item found");
			throw new IllegalArgumentException("No item found");
		}
		List<Item> filteredItems = itemService.getAllItems()
							.stream()
							.filter(item -> items.contains(item.getName()))
							.collect(Collectors.toList());
		return filterResult(filteredItems);
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
	
	private static List<String> getAllElements(String input) {
		return Arrays.asList(input.split("\\s+"));
	}
	
}