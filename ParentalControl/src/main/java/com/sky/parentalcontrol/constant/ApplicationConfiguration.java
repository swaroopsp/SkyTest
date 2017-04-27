package com.sky.parentalcontrol.constant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.sky.parentalcontrol.service.MovieService;
import com.sky.parentalcontrol.service.ParentalControlService;
import com.sky.parentalcontrol.service.impl.MovieServiceImpl;
import com.sky.parentalcontrol.service.impl.ParentalControlServiceImpl;

@Configuration
@PropertySource(value= {"classpath:application.properties"})
public class ApplicationConfiguration {

    @Bean
    public MovieService getMovieService() {
        return new MovieServiceImpl();
    }

    @Bean
    public ParentalControlService getShopService() {
        return new ParentalControlServiceImpl();
    }
}