package com.timeforge.weather.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.timeforge.weather.model.WeatherDTO;

@Service
public interface IWeatherService {

	List<WeatherDTO> fetchWeatherDetails(String zipCode);
}
