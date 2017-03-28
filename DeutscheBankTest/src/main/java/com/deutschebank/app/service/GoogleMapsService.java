package com.deutschebank.app.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import com.deutschebank.app.exception.GoogleMapException;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
/**
 * Created by swaroop on 27/03/2017.
 */
@Service
public class GoogleMapsService {
    @Value("${google.api.key}")
    private String apiKey;

    public GeoPoint getLatLongPositions(String address) {
    	GeoPoint geoLocation = null;
    	if(StringUtils.isNotEmpty(address)){
	        try {
	            GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
	            GeocodingResult[] results = GeocodingApi.geocode(context,
	                    address).await();
	            geoLocation = new GeoPoint(results[0].geometry.location.lat, results[0].geometry.location.lng);
	        } catch (Exception exception) {
	            throw new GoogleMapException("Something went wrong while connecting to  ");
	        }
    	}else{
    		throw new GoogleMapException("Input to GoogleMapsService is empty.");
    	}
        return geoLocation;
    }
}