package com.restaurant.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.restaurant.reservation.entity",
 "com.restaurant.reservation.dao","com.restaurant.reservation.service",
 "com.restaurant.reservation.controller"
})
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

}
