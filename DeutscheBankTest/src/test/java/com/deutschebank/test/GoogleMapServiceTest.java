package com.deutschebank.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deutschebank.app.exception.GoogleMapException;
import com.deutschebank.app.service.GoogleMapsService;
import com.deutschebank.test.configuration.TestApplicationConfiguration;
/**
 * Created by swaroop on 27/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfiguration.class)
@EnableAutoConfiguration
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
		GeoPoint geoPoint = googleMapsService.getLatLongPositions("Argos 6 GU51 3LA");
		assertThat(51.2804565, is(geoPoint.getLat()));
		assertThat(-0.8416743999999999, is(geoPoint.getLon()));
	}
	
	@Test(expected=GoogleMapException.class)
	public void testGetLatLongPositionsEmptyAddress() {
		GeoPoint geoPoint = googleMapsService.getLatLongPositions("");
		assertThat(51.2804565, is(geoPoint.getLat()));
		assertThat(-0.8416743999999999, is(geoPoint.getLon()));
	}
}
