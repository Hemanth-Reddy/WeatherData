package com.weatherAPI.WeatherData.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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

@Service
public class WeatherService implements IWeatherService{

	@Override
	public List<WeatherDTO> fetchWeatherDetails(String zipCode) {
		 String baseUrl = "https://api.openweathermap.org/data/2.5/forecast?zip=79415&cnt=7&appid=9cfa34ec1063f5be1c61f3ec27c8b44c";
	        //log.info("Micro service url to get user details : {}", baseUrl);

	        String response = "";
	        try {
	            URI uri = new URI(baseUrl);
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	            HttpEntity request = new HttpEntity(headers);
	            RestTemplate restTemplate = new RestTemplate();
	            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	            response = responseEntity.getBody();
	        } catch (URISyntaxException use) {
	            use.printStackTrace();
	        }
	        if (response == null || response.isEmpty()) {
	            return null;
	        }
			
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode rootNode=null;
	        JsonNode listNode=null;
			try {
				rootNode = mapper.readTree(response);
				listNode = rootNode.get("list");
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<WeatherDTO> weatherList = new ArrayList<>();
			for(JsonNode node: listNode) {
				WeatherDTO weather = new WeatherDTO();
				weather.setHighTemp(node.get("main").get("temp_max").asDouble());
				weather.setLowTemp(node.get("main").get("temp_min").asDouble());
				weather.setHumidity(node.get("main").get("humidity").asDouble());
				weather.setDescriptiveCondition(node.get("weather").get(0).get("description").asText());
				weather.setDate(node.get("dt_txt").asText());
				weatherList.add(weather);
			}
	        
	        
	        System.out.println("list node 1: " + listNode.get(0).toString());
	        
	        return weatherList;

	}

	
}
