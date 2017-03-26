package com.deutschebank.app.client.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

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
public class Shop {
	@NotNull
	@NotEmpty
	private String shopName;

	@NotNull
	@NotEmpty
	private ShopAddress shopAddress;

	@NotNull
	@NotEmpty
	private GeoPoint geoLocation;

	public String getAddress() {
		return new StringBuilder().append(shopName).append(" ").append(shopAddress.getNumber()).append(" ")
				.append(shopAddress.getPostCode()).toString();
	}
}