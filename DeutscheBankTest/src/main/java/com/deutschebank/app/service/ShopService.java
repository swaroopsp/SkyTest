package com.deutschebank.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import com.deutschebank.app.client.model.Conference;
import com.deutschebank.app.client.model.GeoLocation;
import com.deutschebank.app.client.model.Shop;
import com.deutschebank.app.db.model.ShopDTO;
import com.deutschebank.app.repository.ShopRepository;

public class ShopService {
	@Autowired
	private GoogleMapsService googleMapsService;

	@Autowired
	private ShopRepository<ShopDTO> shopRepository;

	@Autowired
	ElasticsearchOperations operations;

	public ShopDTO saveShop(Shop clientShop) throws Exception {
		GeoLocation geoLocation = googleMapsService.getLatLongPositions(clientShop.getAddress());

		ShopDTO shop = ShopDTO.builder().postCode(clientShop.getShopName())
										.number(clientShop.getShopAddress().getNumber())
										.postCode(clientShop.getShopAddress().getPostCode())
										.latitude(geoLocation.getLatitude())
										.longitude(geoLocation.getLongitude()).build();
		
		if (shopRepository.existsByShopName(clientShop.getShopName())) {
			return shopRepository.updateShopDate(clientShop.getShopName());
		} else {
			return shopRepository.save(shop);
		}
	}

	public List<ShopDTO> getShops(GeoPoint geoLocation) {
		GeoPoint startLocation = new GeoPoint(50.0646501D, 19.9449799D);
		String range = "330mi"; // or 530km
		CriteriaQuery query = new CriteriaQuery(new Criteria("location").within(startLocation, range));

		List<Conference> result = operations.queryForList(query, Conference.class);
		return null;
	}
}
