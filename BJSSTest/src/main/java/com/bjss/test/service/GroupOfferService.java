package com.bjss.test.service;

import org.springframework.stereotype.Service;

import com.bjss.test.model.GroupOffer;

import lombok.Builder;

@Builder
@Service
public class GroupOfferService {
	public GroupOffer getGroupOffer() {
		return GroupOffer.builder().item("Soup").quantity(2).relatedItem("Bread")
						.discountInPercentage(50).build();
	}
}
