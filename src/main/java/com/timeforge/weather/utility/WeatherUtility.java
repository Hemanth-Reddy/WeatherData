package com.timeforge.weather.utility;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.timeforge.weather.entity.Weather;
import com.timeforge.weather.model.WeatherDTO;

/**
 * This is a utility class.
 * @author Hemanth
 *
 */
@Component
public class WeatherUtility {
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(WeatherUtility.class);

	/**
	 * Converts a WeatherDTO object to a Weather entity object and returns the entity object.
	 * @param weatherDTO - the WeatherDTO object to be converted
	 * @return the converted Weather entity object
	 */
	public Weather convertToEntity(WeatherDTO weatherDTO) {
		
		Weather weather = new Weather();
		weather.setDate(weatherDTO.getDate());
		weather.setHighTemp(weatherDTO.getHighTemp());
		weather.setLowTemp(weatherDTO.getLowTemp());
		weather.setDate(weatherDTO.getDate());
		weather.setHumidity(weatherDTO.getHumidity());
		weather.setDescriptiveCondition(weatherDTO.getDescriptiveCondition());
		return weather;
	}

}
