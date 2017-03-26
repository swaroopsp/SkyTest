package com.deutschebank.app.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class ShopDTO {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotEmpty
	private String shopName;
	
	@NotNull
	@NotEmpty
	private String number;
	
	@NotNull
	@NotEmpty
	private String postCode;
	
	@NotNull
	@NotEmpty
	private String latitude;
	
	@NotNull
	@NotEmpty
	private String longitude;
}
