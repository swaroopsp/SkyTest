package com.deutschebank.app.client.model;

import javax.validation.constraints.NotNull;

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
public class ClientAddressDTO {
	@NotNull
	private String houseNumber;
	private String address1;
	private String address2;
	@NotNull
	private String postCode;
	private double latitude;
	private double longituide;
	
	public String getFullAddress() {
		return new StringBuilder()
						.append(houseNumber)
						.append(" ")
						.append(address1)
						.append(" ")
						.append(address2)
						.append(" ")
						.append(postCode)
						.toString();
	}
}