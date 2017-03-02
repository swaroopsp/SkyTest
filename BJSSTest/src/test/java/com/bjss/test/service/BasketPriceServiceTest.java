package com.bjss.test.service;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

import com.bjss.test.mock.MockService;
import com.bjss.test.model.Result;

@RunWith(MockitoJUnitRunner.class)
public class BasketPriceServiceTest {
	
	@Mock
    private ItemService itemService;
 
    @Mock
    private DiscountService discountService;
    
    @InjectMocks
    private BasketPricingService basketPricingService;
    
	@Before
	public void setUp() throws Exception {
		when(itemService.getAllItems()).thenReturn(MockService.mockAllItems());	
		when(discountService.getDisCount()).thenReturn(MockService.mockDisCount());
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetResultWithEmptyInput() {
		basketPricingService.getResult("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetResultWithNoItems() {
		basketPricingService.getResult("PriceBasket");
	}
	
	@Test
	public void testGetResult() {
		Result result = basketPricingService.getResult("PriceBasket Apples Milk Bread");
		assertEquals(result.getSubTotal(), BigDecimal.valueOf(3.10));
		assertEquals(result.getDiscountResults().get(0).getActualDiscount(), BigDecimal.valueOf(0.10));
		assertEquals(result.getDiscountResults().get(0).getDiscountInPercentage(), BigDecimal.valueOf(10));
		assertEquals(result.getDiscountResults().get(0).getName(), "Apples");
	}
	
	@Test
	public void testGetResult_With_InvalidItem() {
		Result result = basketPricingService.getResult("PriceBasket AAA");
		assertEquals(result.getSubTotal(), BigDecimal.valueOf(0));
	}

}
