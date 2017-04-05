package com.deutschebank.app.utils;

import com.deutschebank.app.client.model.GeoLocationDTO;
import com.deutschebank.app.client.model.ShopAddressDTO;
import com.deutschebank.app.client.model.ShopDTO;
import com.deutschebank.app.db.model.Shop;

/**
 * Created by swaroop on 27/03/2017.
 */
public class Shops {
    public static Shop of(ShopDTO shopDTO, GeoLocationDTO geoPoint) {
        return Shop.builder()
                .shopName(shopDTO.getShopName())
                .number(shopDTO.getShopAddressDTO().getNumber())
                .postCode(shopDTO.getShopAddressDTO().getPostCode())
                .latitude(geoPoint.getLatitude())
                .longitude(geoPoint.getLongitude())
                .fullAddress(geoPoint.getFullAddress())
                .build();
    }

    public static ShopDTO of(Shop shop) {
        ShopAddressDTO shopAddressDTO = ShopAddressDTO.builder()
                .number(shop.getNumber())
                .postCode(shop.getPostCode()).build();
        return ShopDTO.builder()
                .shopName(shop.getShopName())
                .shopAddressDTO(shopAddressDTO)
                .build();
    }
}
