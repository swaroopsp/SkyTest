package com.bjss.test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bjss.test.model.DiscountResult;
import com.bjss.test.model.Result;
import com.bjss.test.service.BasketPricingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("resource")
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		printResults(input);
	}
	
	private static void printResults(String input){
		AnnotationConfigApplicationContext ctx = getContext();
		BasketPricingService basketPricingService = ctx.getBean(BasketPricingService.class);
		Result result = basketPricingService.getResult(input);
		if (Optional.of(result).isPresent()) {
			System.out.println(result);
			System.out.println("Subtotal: £" + result.getSubTotal());
			BigDecimal totalDiscount = BigDecimal.ZERO;
			for (DiscountResult discountResult : result.getDiscountResults()) {
				System.out.println(discountResult.getName() + " " + discountResult.getDiscountInPercentage()
								+ "% off: -" + discountResult.getActualDiscount());
				totalDiscount = totalDiscount.add(discountResult.getActualDiscount());
			}
			System.out.println("Total : £" + result.getSubTotal().subtract(totalDiscount));
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
	
}
