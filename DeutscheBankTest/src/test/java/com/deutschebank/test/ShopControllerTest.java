 package com.deutschebank.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.deutschebank.app.App;
import com.deutschebank.app.db.model.Shop;
import com.deutschebank.app.repository.ShopRepository;
import com.deutschebank.app.service.GoogleMapsService;
import com.deutschebank.test.utils.TestUtils;
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
	private int port;

	@Autowired
	@InjectMocks
	private GoogleMapsService googleMapsService;
	
	@Autowired
	private ShopRepository shopRepository;

	@Before
	public void setUp() throws Exception {
		RestAssured.port = port;
	}

	@Test
	public void testAddShop() throws Exception {
		String result = new TestUtils().getFile("input.json");

		given().contentType("application/json").body(result).when().post("/shops/shop").then().statusCode(200)
						.body("version", equalTo(0));
	}

	@Test
	public void testAddShopMissingInput() throws Exception {
		String result = new TestUtils().getFile("input_missing_entries.json");
		given().contentType("application/json").body(result).when().post("/shops/shop").then().statusCode(500);
	}
	
	@Test
	public void testGetClosestShop() throws Exception {
		Shop shopDTO2 = Shop.builder().shopName("Asda").number("").postCode("GU14 7LT").fullAddress("Asda, Westmead, Farnborough GU14 7LT").build();
		Shop shopDTO3 = Shop.builder().shopName("tesco").number("5").postCode("GU11 1SQ").fullAddress("Tesco, 5 Wellington Ave, Aldershot GU11 1SQ").build();
		Shop shopDTO1 = Shop.builder().shopName("Argos").number("").postCode("GU14 7SJ").fullAddress("Argos, 6, The Hart Centre, Fleet Rd, Fleet GU51 3LA").build();
		shopRepository.save(shopDTO1);
		shopRepository.save(shopDTO2);
		shopRepository.save(shopDTO3);
		given().contentType("application/json").param("adrress", "1, champion way, fleet, gu526ht").when().get("/shops/shop/nearest").then().statusCode(200);
	}
	
	@Test
	public void testGetClosestShopMissingInput() throws Exception {
		given().contentType("application/json").param("adrress", "").when().get("/shops/shop/nearest").then().statusCode(500);
	}
}