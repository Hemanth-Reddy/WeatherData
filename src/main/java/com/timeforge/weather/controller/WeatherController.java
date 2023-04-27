package com.timeforge.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timeforge.weather.model.WeatherDTO;
import com.timeforge.weather.service.IWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller class for fetching weather information..
 * @author Hemanth
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private IWeatherService iWeatherService;
	
	private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

	/**
	 * Fetches weather information for a given zip code.
	 * 
	 * @param zipCode The zip code for which to fetch weather information.
	 * @return A list of WeatherDTO objects containing weather information.
	 */
	@GetMapping("/{zipCode}")
	public List<WeatherDTO> fetchWeatherInfo(@PathVariable String zipCode) {
		log.info("fetching weather details for zip code: {}", zipCode);
		return iWeatherService.fetchWeatherDetails(zipCode);
	}

}
