package com.cschool.scooterrentalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@PropertySource("classpath:message.properties")
public class ScooterRentalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScooterRentalAppApplication.class, args);
    }

}