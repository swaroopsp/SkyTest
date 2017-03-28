package com.deutschebank.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deutschebank.app.App;
import com.deutschebank.app.Shops;
import com.deutschebank.app.client.model.ShopDTO;
import com.deutschebank.app.db.model.Shop;
import com.deutschebank.app.repository.ShopRepository;

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

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testConcurrencyWriting() {
		assertEquals("Number of entry in Database incorrect.", 3, shopRepository.count());
		Shop shopOne = shopRepository.findByShopName("Argos");
		Shop shopTwo = shopRepository.findByShopName("Argos");

		// At first, serialize the movies to transport them to a view.
		ShopDTO shopDTOOne = Shops.of(shopOne);
		ShopDTO shopDTOTwo = Shops.of(shopTwo);

		// The view modifies the transport objects.
		shopDTOOne.setVersion(1);
		shopDTOTwo.setVersion(0);
		GeoPoint geoPoint = new GeoPoint(1.1, 1.1);
		// The view sends the transport objects to the backend.
		Shop theUpdatedShopOne = Shops.of(shopDTOOne, geoPoint);
		Shop theUpdatedShopTwo = Shops.of(shopDTOTwo, geoPoint);

		// The versions of the updateded movies are both 0.
		assertEquals(0, theUpdatedShopOne.getVersion());
		assertEquals(0, theUpdatedShopTwo.getVersion());

		// The backend tries to save both.
		shopRepository.save(theUpdatedShopOne);

		// OUTCH! Exception!
		shopRepository.save(theUpdatedShopTwo);
	}
}
