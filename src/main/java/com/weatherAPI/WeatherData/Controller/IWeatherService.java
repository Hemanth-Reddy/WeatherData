package com.weatherAPI.WeatherData.Controller;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IWeatherService {

	List<WeatherDTO> fetchWeatherDetails(String zipCode);
}
