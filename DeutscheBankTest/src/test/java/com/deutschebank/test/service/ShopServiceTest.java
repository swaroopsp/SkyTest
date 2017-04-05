package com.deutschebank.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deutschebank.app.App;
import com.deutschebank.app.client.model.ShopDistanceDTO;
import com.deutschebank.app.db.model.Shop;
import com.deutschebank.app.repository.ShopRepository;
import com.deutschebank.app.service.ShopService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ShopServiceTest {
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ShopService ShopService;

	@Before
	public void setUp() throws Exception {
		Shop shopDTO2 = Shop.builder().shopName("Asda").number("").postCode("GU14 7LT").fullAddress("Asda, Westmead, Farnborough GU14 7LT").build();
		Shop shopDTO3 = Shop.builder().shopName("tesco").number("5").postCode("GU11 1SQ").fullAddress("Tesco, 5 Wellington Ave, Aldershot GU11 1SQ").build();
		Shop shopDTO1 = Shop.builder().shopName("Argos").number("").postCode("GU14 7SJ").fullAddress("Argos, 6, The Hart Centre, Fleet Rd, Fleet GU51 3LA").build();
		
		shopRepository.save(shopDTO1);
		shopRepository.save(shopDTO2);
		shopRepository.save(shopDTO3);
	}

	@After
	public void tearDown(){
		shopRepository.deleteAll();
	}

	@Test
	public void testGetNearestShop() {
		ShopDistanceDTO distanceDTO = ShopService.getNearestShop("1, champion way, fleet, gu526ht");
		assertEquals("Argos, 6, The Hart Centre, Fleet Rd, Fleet GU51 3LA", distanceDTO.getShopFullAddress());
	}
}
