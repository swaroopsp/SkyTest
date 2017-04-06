package com.deutschebank.app.service;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deutschebank.app.client.model.GeoLocationDTO;
import com.deutschebank.app.exception.GoogleMapException;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Unit;

import lombok.extern.slf4j.Slf4j;
/**
 * Created by swaroop on 27/03/2017.
 */
@Service
@Slf4j
public class GoogleMapsService {
    @Value("${google.api.key}")
    private String apiKey;
    
    @Value("${google.api.distance.matrix.key}")
    private String apiKeyDistanceMatrix;

    public GeoLocationDTO getLatLongPositions(String address) {
    	GeoLocationDTO geoLocation = null;
    	if(StringUtils.isNotEmpty(address)){
	        try {
	            GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
	            GeocodingResult[] results = GeocodingApi.geocode(context,
	                    address).await();
	            geoLocation = GeoLocationDTO.builder().latitude(results[0].geometry.location.lat)
	            									  .longitude(results[0].geometry.location.lng)
	            									  .fullAddress(results[0].formattedAddress)
	            									  .build();
	        } catch (Exception exception) {
	        	final String message = "Something went wrong while connecting to google geocode service"; 
	        	log.error(message);
	            throw new GoogleMapException(exception, message);
	        }
    	}else{
    		throw new GoogleMapException("Input to GoogleMapsService can not be empty.");
    	}
        return geoLocation;
    }
    
	public long getDistance(String origins, String destinations) {
		if(StringUtils.isNotEmpty(origins) && StringUtils.isNotEmpty(destinations)){
			GeoApiContext context = new GeoApiContext().setApiKey(apiKeyDistanceMatrix);
			DistanceMatrix results = null;
			try {
				results = DistanceMatrixApi
								.getDistanceMatrix(context, new String[] { origins }, new String[] { destinations })
								.units(Unit.METRIC).await();
			} catch (ApiException | InterruptedException | IOException exception) {
				final String message = "Something went wrong while connecting to google distance matrix service";
				log.error(message);
				throw new GoogleMapException(exception, message);
			}
			return results.rows[0].elements[0].distance.inMeters / 1000;
		}else{
    		throw new GoogleMapException("Input to Distance matrix Service can not be empty.");
    	}
	}
}