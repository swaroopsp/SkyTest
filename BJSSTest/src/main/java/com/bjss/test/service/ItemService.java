package com.bjss.test.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bjss.test.model.Item;

import lombok.Builder;

@Builder
@Service
public class ItemService {
	
	public List<Item> getAllItems() {
		Item item1 = Item.builder().name("Soup").price(BigDecimal.valueOf(0.65)).build();
		Item item2 = Item.builder().name("Bread").price(BigDecimal.valueOf(0.80)).build();
		Item item3 = Item.builder().name("Milk").price(BigDecimal.valueOf(1.30)).build();
		Item item4 = Item.builder().name("Apples").price(BigDecimal.valueOf(1.00)).build();
		
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		return items;
	}
}
