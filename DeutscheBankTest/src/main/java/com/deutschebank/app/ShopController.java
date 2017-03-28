package com.deutschebank.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deutschebank.app.client.model.ShopDTO;
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

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public VersionDTO addShop(@RequestBody ShopDTO clientShopDTO) {
		return shopService.addShop(clientShopDTO);
	}
}