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
public class ShopAddressDTO {
	@NotNull
	@NotBlank
	private String number;
	
	@NotNull
	@NotBlank
	private String postCode;
}