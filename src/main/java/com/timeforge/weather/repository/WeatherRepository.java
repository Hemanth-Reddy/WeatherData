package com.timeforge.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timeforge.weather.entity.Weather;

public interface WeatherRepository extends JpaRepository<Weather, String> {

}

