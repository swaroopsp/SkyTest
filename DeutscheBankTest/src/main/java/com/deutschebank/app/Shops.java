package com.deutschebank.app;

import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.deutschebank.app.client.model.ShopAddressDTO;
import com.deutschebank.app.client.model.ShopDTO;
import com.deutschebank.app.db.model.Shop;

/**
 * Created by swaroop on 27/03/2017.
 */
public class Shops {
    public static Shop of(ShopDTO shopDTO, GeoPoint geoPoint) {
        return Shop.builder()
                .shopName(shopDTO.getShopName())
                .number(shopDTO.getShopAddressDTO().getNumber())
                .postCode(shopDTO.getShopAddressDTO().getPostCode())
                .latitude(geoPoint.getLat())
                .longitude(geoPoint.getLon())
                .build();
    }

    public static ShopDTO of(Shop shop) {
        ShopAddressDTO shopAddressDTO = ShopAddressDTO.builder()
                .number(shop.getNumber())
                .postCode(shop.getPostCode()).build();
        GeoPoint geoPoint = new GeoPoint(shop.getLatitude(), shop.getLongitude());
        return ShopDTO.builder()
                .shopName(shop.getShopName())
                .shopAddressDTO(shopAddressDTO)
                .build();

    }
}
