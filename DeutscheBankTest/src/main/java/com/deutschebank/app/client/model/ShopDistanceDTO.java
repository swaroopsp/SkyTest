package com.deutschebank.app.client.model;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Builder;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShopDistanceDTO implements Comparator<ShopDistanceDTO>{
	private String shopName;
	private long distance;
	private String shopFullAddress;
	
	@Override
	public int compare(ShopDistanceDTO shopDistance1, ShopDistanceDTO shopDistance2) {
		return (int) (shopDistance1.getDistance() - shopDistance2.getDistance());
	}
}