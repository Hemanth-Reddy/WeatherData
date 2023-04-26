package com.weatherAPI.WeatherData.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherAPI.WeatherData.Controller.Weather;

public interface WeatherRepository extends JpaRepository<Weather, String> {

}

