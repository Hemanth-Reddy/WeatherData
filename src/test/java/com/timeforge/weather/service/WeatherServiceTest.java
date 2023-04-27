package com.timeforge.weather.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.timeforge.weather.model.WeatherDTO;
import com.timeforge.weather.repository.WeatherRepository;
import com.timeforge.weather.utility.WeatherUtility;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

	@InjectMocks
	private WeatherService weatherService;

	@Mock
	private WeatherRepository weatherRepository;

	@Mock
	private WeatherUtility weatherUtility;

	@BeforeEach
	void setUp() {
	}

	@Test
	public void fetchWeatherDetailsTest() {
		// given
		String zipCode = "79415";
		String response = "{\"list\": [{\"main\": {\"temp_max\": 20.0,\"temp_min\": 10.0,\"humidity\": 70},\"weather\": [{\"description\": \"sunny\"}],\"dt_txt\": \"2023-04-26 06:00:00\"}],\"city\": {\"name\": \"Lubbock\"}}";
		RestTemplate restTemplate = mock(RestTemplate.class);
		when(restTemplate.getForObject(weatherService.buildUrl(zipCode), String.class)).thenReturn(response);

		// when
		List<WeatherDTO> weatherDTOList = weatherService.fetchWeatherDetails(zipCode);

		 // then
		assertEquals(1, weatherDTOList.size());
		assertEquals(20.0, weatherDTOList.get(0).getHighTemp());
		assertEquals(10.0, weatherDTOList.get(0).getLowTemp());
		assertEquals(70, weatherDTOList.get(0).getHumidity());
		assertEquals("sunny", weatherDTOList.get(0).getDescriptiveCondition());
		assertEquals("2023-04-26 06:00:00", weatherDTOList.get(0).getDate());
	}
}
