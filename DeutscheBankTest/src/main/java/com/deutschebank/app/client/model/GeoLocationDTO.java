package com.deutschebank.app.client.model;

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
public class GeoLocationDTO {
	private String latitude;
	private String longitude;
}