package com.deutschebank.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.deutschebank.app.Shops;
import com.deutschebank.app.client.model.ClientAddressDTO;
import com.deutschebank.app.client.model.GeoLocationDTO;
import com.deutschebank.app.client.model.ShopDTO;
import com.deutschebank.app.client.model.ShopDistanceDTO;
import com.deutschebank.app.client.model.VersionDTO;
import com.deutschebank.app.db.model.Shop;
import com.deutschebank.app.exception.ApplicationException;
import com.deutschebank.app.repository.ShopRepository;

/**
 * Created by swaroop on 27/03/2017.
 */
public class ShopService {
	@Autowired
	private GoogleMapsService googleMapsService;

	@Autowired
	private ShopRepository shopRepository;

	@Transactional
	public VersionDTO addShop(ShopDTO shopDTO) {
		GeoLocationDTO geoPoint = googleMapsService.getLatLongPositions(shopDTO.getFullAddress());
		Shop existingShopDTODTO = shopRepository.findByShopName(shopDTO.getShopName());
		if (existingShopDTODTO != null) {
			shopRepository.updateShop(shopDTO.getShopName(), existingShopDTODTO.getVersion() + 1);
			return VersionDTO.builder().version(shopRepository.findByShopName(shopDTO.getShopName()).getVersion())
							.build();
		} else {
			Shop shop = Shops.of(shopDTO, geoPoint);
			shop = shopRepository.save(shop);
			return VersionDTO.builder().version(shop.getVersion()).build();
		}
	}
	
	//If the shopRepository.findAll() returns millions of rows the this solution is not good.
	//Then possibly use mongo Geo spatial document search feature
	public ShopDistanceDTO getNearestShop(String address) {
		Optional<String> optionalProject = Optional.ofNullable(address);
		if (optionalProject.isPresent()) {
			Iterable<Shop> iterable = shopRepository.findAll();
			Iterator<Shop> iterator = iterable.iterator();
			List<ShopDistanceDTO> shopDistances = new ArrayList<>();
			while (iterator.hasNext()) {
				Shop shopDTO = iterator.next();
				shopDistances.add(ShopDistanceDTO.builder()
								.shopName(shopDTO.getShopName())
								.shopFullAddress(shopDTO.getFullAddress())
								.distance(googleMapsService.getDistance(address, shopDTO.getFullAddress()))
								.build());
			}
			Collections.sort(shopDistances, new ShopDistanceDTO());
			return shopDistances.get(0);
		}else{
			throw new ApplicationException("Client address is null"); 
		}
	}
}
