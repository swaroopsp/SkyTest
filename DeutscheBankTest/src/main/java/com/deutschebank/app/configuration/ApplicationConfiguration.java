package com.deutschebank.app.configuration;

import com.deutschebank.app.service.GoogleMapsService;
import com.deutschebank.app.service.ShopService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value= {"classpath:application.properties"})

/**
 * Created by swaroop on 27/03/2017.
 */
public class ApplicationConfiguration {

    @Bean
    public GoogleMapsService getGoogleMapsService() {
        return new GoogleMapsService();
    }

    @Bean
    public ShopService getShopService() {
        return new ShopService();
    }
}
