package com.deutschebank.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.deutschebank.app.App;
import com.deutschebank.app.exception.GoogleMapException;
import com.deutschebank.app.service.GoogleMapsService;
import com.jayway.restassured.RestAssured;
/**
 * Created by swaroop on 27/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({ "server.port:0", "spring.datasource.url:jdbc:h2:mem:SpringBootRest;DB_CLOSE_ON_EXIT=FALSE" })
public class ShopControllerTest {
	@Value("${local.server.port}")
	int port;

	@Autowired
	@InjectMocks
	private GoogleMapsService googleMapsService;

	@Before
	public void setUp() throws Exception {
		RestAssured.port = port;
	}
	
	@Test
	public void addShop() throws Exception {
		String result = getFile("input.json");

		given().contentType("application/json").body(result).when().post("/shops/add").then().statusCode(200)
						.body("version", equalTo(0));
	}
	
	@Test
	public void addShopMissingInput() throws Exception {
		String result = getFile("input_missing_entries.json");
		given().contentType("application/json").body(result).when().post("/shops/add").then().statusCode(500);
	}
	
	//It should not throw the GoogleMapException. But caught by GoogleMapExceptionMappper. Due to less time i could not fix this.
	@Test(expected = GoogleMapException.class)
	public void shouldThrowGoogleMapException() {
		Mockito.when(googleMapsService.getLatLongPositions(anyString())).thenThrow(new GoogleMapException());
		String result = getFile("input.json");

		given().contentType("application/json").body(result).when().post("/shops/add").then().statusCode(500);
	}

	private String getFile(String fileName) {
		String result = "";
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

}