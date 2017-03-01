package com.bjss.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bjss.test.model.DiscountResult;
import com.bjss.test.model.Result;
import com.bjss.test.service.BasketPricingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("resource")
public class App {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		if (!Optional.of(input).isPresent()) {
			log.error("Input can not be empty");
			throw new IllegalArgumentException("Input can not be empty");
		}

		List<String> elements = getAllElements(input);

		elements = elements.subList(1, elements.size());
		if (elements.size() == 0) {
			log.error("No item found");
			throw new IllegalArgumentException("No item found");
		}

		printResults(elements);
	}
	
	private static void printResults(List<String> elements){
		AnnotationConfigApplicationContext ctx = getContext();
		BasketPricingService basketPricingService = ctx.getBean(BasketPricingService.class);
		Result result = basketPricingService.getResult(elements);
		if (Optional.of(result).isPresent()) {
			System.out.println(result);
			System.out.println("Subtotal: £" + result.getSubTotal());
			BigDecimal totalDiscount = BigDecimal.ZERO;
			for (DiscountResult discountResult : result.getDiscountResults()) {
				System.out.println(discountResult.getName() + " " + discountResult.getDiscountInPercentage()
								+ "% off: -" + discountResult.getActualDiscount());
				totalDiscount = totalDiscount.add(discountResult.getActualDiscount());
			}
			System.out.println("Total price: £" + result.getSubTotal().subtract(totalDiscount));
		} else {
			log.info("No Results found");
		}
	}

	private static AnnotationConfigApplicationContext getContext(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		return ctx;
	}
	
	private static List<String> getAllElements(String input) {
		return Arrays.asList("PriceBasket Apples Milk Bread".split("\\s+"));
	}

}
