package com.deutschebank.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.deutschebank.app.client.model.GeoLocation;
import com.deutschebank.app.client.model.Shop;
import com.deutschebank.app.db.model.ShopDTO;
import com.deutschebank.app.repository.ShopRepository;

public class ShopService {
	@Autowired
	private GoogleMapsService googleMapsService;
	
	@Autowired
	private ShopRepository shopRepository;
	
	public void saveShop(Shop clientShop) throws Exception{
		GeoLocation geoLocation = googleMapsService.getLatLongPositions(clientShop.getAddress());
		
		ShopDTO shop = ShopDTO.builder().postCode(clientShop.getShopName())
													.number(clientShop.getShopAddress().getNumber())
													.postCode(clientShop.getShopAddress().getPostCode())
													.latitude(geoLocation.getLatitude())
													.longitude(geoLocation.getLongitude()).build();
		shopRepository.save(shop);
	}
	
	public List<ShopDTO> getShops(GeoLocation geoLocation){
		return null;
	}
}
