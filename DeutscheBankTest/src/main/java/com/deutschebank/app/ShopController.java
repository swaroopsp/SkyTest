package com.deutschebank.app;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deutschebank.app.client.model.GeoLocation;
import com.deutschebank.app.client.model.Shop;
import com.deutschebank.app.service.ShopService;

@RestController
public class ShopController {
	
	@Autowired
	private ShopService shopService; 
	
	@RequestMapping("/shops")
	@POST
	public void addShop(Shop clientShop) throws Exception {	
		shopService.saveShop(clientShop);
	}
	
	@RequestMapping("/shops")
	@GET
	public void getShop(GeoLocation geoLocation) throws Exception {	
//		shopService.saveShop(geoLocation);
	}
}
