package com.deutschebank.test.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.deutschebank.app.exception.mapper.GenericExceptionMapper;
import com.deutschebank.app.exception.mapper.GoogleMapExceptionMapper;
import com.deutschebank.app.service.GoogleMapsService;
/**
 * Created by swaroop on 27/03/2017.
 */
@Configuration
@PropertySource(value= {"classpath:test.properties"})
public class TestApplicationConfiguration extends ResourceConfig{

    @Bean
    public GoogleMapsService getGoogleMapsService() {
        return new GoogleMapsService();
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    public TestApplicationConfiguration() {
    	packages("com.deutschebank.app");
        register(new GoogleMapExceptionMapper());
        register(new GenericExceptionMapper());
        register(new GoogleMapExceptionMapper());
    }
}

