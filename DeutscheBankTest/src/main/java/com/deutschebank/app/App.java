package com.deutschebank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.deutschebank.app"})
@EnableJpaRepositories("com.deutschebank.app.repository")
@EntityScan("com.deutschebank.app.db.model")
/**
 * Created by swaroop on 27/03/2017.
 */
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}