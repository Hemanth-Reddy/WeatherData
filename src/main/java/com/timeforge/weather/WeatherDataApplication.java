package com.timeforge.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController("/")
public class WeatherDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDataApplication.class, args);
	}
	
	/*
	 * @GetMapping("hello") public String hello() { return "hello"; }
	 */

}
