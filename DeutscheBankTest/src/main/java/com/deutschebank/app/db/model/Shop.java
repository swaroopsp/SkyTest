package com.deutschebank.app.db.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Builder;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
/**
 * Created by swaroop on 27/03/2017.
 */
public class Shop {
	@Version
	private long version;

	@NotNull
	@NotEmpty
	@Id
	private String shopName;

	@NotNull
	@NotEmpty
	private String number;

	@NotNull
	@NotEmpty
	private String postCode;

	private double latitude;

	private double longitude;

}