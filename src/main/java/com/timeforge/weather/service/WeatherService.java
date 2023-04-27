package com.timeforge.weather.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timeforge.weather.controller.WeatherController;
import com.timeforge.weather.entity.Weather;
import com.timeforge.weather.model.WeatherDTO;
import com.timeforge.weather.repository.WeatherRepository;
import com.timeforge.weather.utility.WeatherUtility;

/**
 * 
 * This class represents the implementation of the IWeatherService interface
 * that provides methods to fetch weather details using an external API and save
 * the details in the database.
 * 
 * @author Hemanth
 */
@Service
public class WeatherService implements IWeatherService {

	@Value("${base.url}")
	private String url;

	@Value("${app.key}")
	private String appKey;

	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	private WeatherUtility weatherUtility;

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(WeatherService.class);

	/**
	 * 
	 * This method fetches weather details for a given zip code using an external
	 * API and saves the details in the database.
	 * 
	 * @param zipCode the zip code for which the weather details need to be fetched.
	 * 
	 * @return a list of WeatherDTO objects representing the weather details.
	 */
	public List<WeatherDTO> fetchWeatherDetails(String zipCode) {
	    String url = buildUrl(zipCode);
	    log.debug("api url to fetch weather details: {}", url);
	    String response = callApi(url);
	    if (response == null || response.isEmpty()) {
	        return null;
	    }
	    return parseResponse(response);
	}

	public String buildUrl(String zipCode) {
	    String baseUrl = url + "?zip=" + zipCode + "&cnt=7&appid=" + appKey;
	    return baseUrl;
	}

	private String callApi(String url) {
	    String response = "";
	    try {
	        URI uri = new URI(url);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	        HttpEntity request = new HttpEntity(headers);
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	        response = responseEntity.getBody();
	    } catch (URISyntaxException use) {
	        use.printStackTrace();
	    }
	    return response;
	}

	private List<WeatherDTO> parseResponse(String response) {
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode rootNode = null;
	    JsonNode listNode = null;
	    try {
	        rootNode = mapper.readTree(response);
	        listNode = rootNode.get("list");
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	    }

	    List<WeatherDTO> weatherList = new ArrayList<>();
	    for (JsonNode node : listNode) {
	        WeatherDTO weather = new WeatherDTO();
	        weather.setHighTemp(node.get("main").get("temp_max").asDouble());
	        weather.setLowTemp(node.get("main").get("temp_min").asDouble());
	        weather.setHumidity(node.get("main").get("humidity").asInt());
	        weather.setDescriptiveCondition(node.get("weather").get(0).get("description").asText());
	        weather.setDate(node.get("dt_txt").asText());

	        Weather weatherEntity = weatherUtility.convertToEntity(weather);
	        weatherRepository.save(weatherEntity);
	        weatherList.add(weather);
	    }
	    log.info("weather list size is: {}", weatherList.size());
	    return weatherList;
	}


}
