package com.deutschebank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deutschebank.app.client.model.ShopDTO;
import com.deutschebank.app.client.model.ShopDistanceDTO;
import com.deutschebank.app.client.model.VersionDTO;
import com.deutschebank.app.service.ShopService;

@RestController
@RequestMapping("/shops")
/**
 * Created by swaroop on 27/03/2017.
 */
public class ShopController {
	@Autowired
	private ShopService shopService;

	@RequestMapping(value = "/shop", method = RequestMethod.POST)
	public VersionDTO addShop(@RequestBody ShopDTO clientShopDTO) {
		return shopService.addShop(clientShopDTO);
	}
	
	@RequestMapping(value = "/shop/nearest", method = RequestMethod.GET)
	public ShopDistanceDTO getClosestShop(@RequestParam String adrress) {
		return shopService.getNearestShop(adrress);
	}
}