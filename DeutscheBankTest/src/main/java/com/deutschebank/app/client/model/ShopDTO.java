package com.deutschebank.app.client.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
/**
 * Created by swaroop on 27/03/2017.
 */
public class ShopDTO {
	@NotNull
	@NotBlank	
	private String shopName;
	@NotNull
	private ShopAddressDTO shopAddressDTO;
	private long version;

	public String getFullAddress() {
		return new StringBuilder().append(shopName).append(" ").append(shopAddressDTO.getNumber()).append(" ")
						.append(shopAddressDTO.getPostCode()).toString();
	}

}