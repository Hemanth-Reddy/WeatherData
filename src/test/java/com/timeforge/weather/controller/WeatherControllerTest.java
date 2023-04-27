package com.timeforge.weather.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.timeforge.weather.model.WeatherDTO;
import com.timeforge.weather.service.IWeatherService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @InjectMocks
    private WeatherController weatherController;

    @MockBean
    private IWeatherService iWeatherService;

    @Mock
    private Logger log;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFetchWeatherInfo() throws Exception {
        // Mock the weather service to return a list of weather DTOs
        List<WeatherDTO> weatherDTOList = new ArrayList<>();
        WeatherDTO weatherDTO1 = new WeatherDTO();
        weatherDTO1.setHighTemp(90);
        weatherDTOList.add(weatherDTO1);
        WeatherDTO weatherDTO2 = new WeatherDTO();
        weatherDTO2.setLowTemp(62);
        weatherDTOList.add(weatherDTO2);
        when(iWeatherService.fetchWeatherDetails("12345")).thenReturn(weatherDTOList);

        // Perform the GET request to the /weather/12345 endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/weather/12345"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert that the response body contains the expected JSON data
        String expectedJson = "[{\"date\":null,\"highTemp\":90.0,\"lowTemp\":0.0,\"humidity\":0,\"descriptiveCondition\":null},{\"date\":null,\"highTemp\":0.0,\"lowTemp\":62.0,\"humidity\":0,\"descriptiveCondition\":null}]";
        String actualJson = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedJson, actualJson, true);
        
        assertEquals(200, result.getResponse().getStatus());
    }
}
