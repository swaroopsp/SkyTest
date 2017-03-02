package com.bjss.test.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bjss.test.model.Discount;
import com.bjss.test.model.GroupOffer;
import com.bjss.test.model.Item;
/**
 * Mocks the items returned from All the services.
 * @author swaroop
 *
 */
public class MockService {
	public static List<Item> mockAllItems() {
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
	
	public static List<Discount> mockDisCount() {
		Discount discount1 = Discount.builder().name("Apples").discountInPercentage(BigDecimal.valueOf(10)).groupOffer(null).build();
		Discount discount2 = Discount.builder().name("Soup").discountInPercentage(BigDecimal.valueOf(0)).groupOffer(mockGroupOffer())
						.build();
		List<Discount> discounts = new ArrayList<>();
		discounts.add(discount1);
		discounts.add(discount2);
		return discounts;
	}
	
	public static GroupOffer mockGroupOffer() {
		return GroupOffer.builder().item("Soup").quantity(2).relatedItem("Bread")
						.discountInPercentage(50).build();
	}
}
