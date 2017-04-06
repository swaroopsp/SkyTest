package com.deutschebank.test.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deutschebank.app.App;
import com.deutschebank.app.client.model.GeoLocationDTO;
import com.deutschebank.app.exception.GoogleMapException;
import com.deutschebank.app.service.GoogleMapsService;
/**
 * Created by swaroop on 27/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class GoogleMapServiceTest {

	@Autowired
	private GoogleMapsService googleMapsService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetLatLongPositions() {
		GeoLocationDTO geoPoint = googleMapsService.getLatLongPositions("Argos 6 GU51 3LA");
		assertThat(51.2804565, is(geoPoint.getLatitude()));
		assertThat(-0.8416743999999999, is(geoPoint.getLongitude()));
	}
	
	@Test(expected=GoogleMapException.class)
	public void testGetLatLongPositionsEmptyAddress() {
		GeoLocationDTO geoPoint = googleMapsService.getLatLongPositions("");
		assertThat(51.2804565, is(geoPoint.getLatitude()));
		assertThat(-0.8416743999999999, is(geoPoint.getLongitude()));
	}
	
	@Test
	public void testGetDistance(){
		long distance = googleMapsService.getDistance("1 champion wy, fleet, gu526ht", "Argos, 6, The Hart Centre, Fleet Rd, Fleet GU51 3LA");
		assertEquals(2, distance);
	}
	
	@Test(expected=GoogleMapException.class)
	public void testGetDistanceSourceEmpty(){
		googleMapsService.getDistance("", "Argos, 6, The Hart Centre, Fleet Rd, Fleet GU51 3LA");
	}
	
	@Test(expected=GoogleMapException.class)
	public void testGetDistanceDistanceEmpty(){
		googleMapsService.getDistance("1 champion wy, fleet, gu526ht", "");
	}
}
