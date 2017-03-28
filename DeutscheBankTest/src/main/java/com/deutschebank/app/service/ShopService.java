package com.deutschebank.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.deutschebank.app.Shops;
import com.deutschebank.app.client.model.ShopDTO;
import com.deutschebank.app.client.model.VersionDTO;
import com.deutschebank.app.db.model.Shop;
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
		GeoPoint geoPoint = googleMapsService.getLatLongPositions(shopDTO.getFullAddress());
		com.deutschebank.app.db.model.Shop existingShopDTODTO = shopRepository.findByShopName(shopDTO.getShopName());
		if (existingShopDTODTO != null) {
			shopRepository.updateShop(shopDTO.getShopName(), existingShopDTODTO.getVersion() + 1);
			return VersionDTO.builder().version(shopRepository.findByShopName(shopDTO.getShopName()).getVersion()).build();
		} else {
			Shop shop = Shops.of(shopDTO, geoPoint);
			shop = shopRepository.save(shop);
			return VersionDTO.builder().version(shop.getVersion()).build();
		}
	}
}
