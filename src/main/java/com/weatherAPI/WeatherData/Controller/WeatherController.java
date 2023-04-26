package com.weatherAPI.WeatherData.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController("/weather")
@Slf4j
public class WeatherController {

	@Autowired
	private IWeatherService iWeatherService;
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeatherController.class);

	@GetMapping("{zipCode}")
	public List<WeatherDTO> fetchWeatherInfo(@PathVariable String zipCode) {
		log.info("dxgfcgvhbjnkml");
		return iWeatherService.fetchWeatherDetails(zipCode);
	}

}
