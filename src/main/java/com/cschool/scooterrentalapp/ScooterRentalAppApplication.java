package com.cschool.scooterrentalapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@PropertySource("classpath:message.properties")
@ComponentScan(basePackages = {"com.cschool.scooterrentalapp.controller",
        "com.cschool.scooterrentalapp.service",
        "com.cschool.scooterrentalapp.domain.model",
        "com.cschool.scooterrentalapp.domain.repository",
        "com.cschool.scooterrentalapp.common",
		"com.cschool.scooterrentalapp.aspect"})
@EnableAspectJAutoProxy( proxyTargetClass = true)
public class ScooterRentalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScooterRentalAppApplication.class, args);
    }

}