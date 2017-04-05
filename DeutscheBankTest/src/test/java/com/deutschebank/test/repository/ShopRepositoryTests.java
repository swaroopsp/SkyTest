package com.deutschebank.test.repository;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deutschebank.app.App;
import com.deutschebank.app.client.model.GeoLocationDTO;
import com.deutschebank.app.client.model.ShopDTO;
import com.deutschebank.app.db.model.Shop;
import com.deutschebank.app.repository.ShopRepository;
import com.deutschebank.app.utils.Shops;

/**
 * Created by swaroop on 27/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ShopRepositoryTests {
	@Autowired
	private ShopRepository shopRepository;

	@Before
	public void setUp() throws Exception {
		Shop shopDTO1 = Shop.builder().shopName("Argos").number("6").postCode("GU51 3LA").build();
		Shop shopDTO2 = Shop.builder().shopName("Asda").number("1").postCode("GU51 3LA").build();
		Shop shopDTO3 = Shop.builder().shopName("tesco").number("3").postCode("GU51 3LA").build();
		shopRepository.save(shopDTO1);
		shopRepository.save(shopDTO2);
		shopRepository.save(shopDTO3);
	}
	
	@After
	public void tearDown(){
		shopRepository.deleteAll();
	}

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testConcurrencyWriting() {
		assertEquals("Number of entry in Database incorrect.", 3, shopRepository.count());
		Shop shopOne = shopRepository.findByShopName("Argos");
		Shop shopTwo = shopRepository.findByShopName("Argos");

		ShopDTO shopDTOOne = Shops.of(shopOne);
		ShopDTO shopDTOTwo = Shops.of(shopTwo);

		shopDTOOne.setVersion(1);
		shopDTOTwo.setVersion(0);
		GeoLocationDTO geoPoint = GeoLocationDTO
						.builder()
						.latitude(1.1)
						.longitude(1.1)
						.build();

		Shop theUpdatedShopOne = Shops.of(shopDTOOne, geoPoint);
		Shop theUpdatedShopTwo = Shops.of(shopDTOTwo, geoPoint);

		assertEquals(0, theUpdatedShopOne.getVersion());
		assertEquals(0, theUpdatedShopTwo.getVersion());

		shopRepository.save(theUpdatedShopOne);

		// OUTCH! Exception!
		shopRepository.save(theUpdatedShopTwo);
	}
}
