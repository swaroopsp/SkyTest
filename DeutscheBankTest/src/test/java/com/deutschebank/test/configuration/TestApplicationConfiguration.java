package com.deutschebank.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.deutschebank.app.service.GoogleMapsService;
/**
 * Created by swaroop on 27/03/2017.
 */
@Configuration
@PropertySource(value= {"classpath:test.properties"})
public class TestApplicationConfiguration {

    @Bean
    public GoogleMapsService getGoogleMapsService() {
        return new GoogleMapsService();
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

