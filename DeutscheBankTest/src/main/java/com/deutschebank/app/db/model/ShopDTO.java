package com.deutschebank.app.db.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

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
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	@CreatedDate
	private Date createdDate;

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

	@NotNull
	@NotEmpty
	private String latitude;

	@NotNull
	@NotEmpty
	private String longitude;
}
