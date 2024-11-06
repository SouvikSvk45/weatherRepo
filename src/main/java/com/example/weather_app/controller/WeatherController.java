package com.example.weather_app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.weather_app.model.WeatherResp;
import com.example.weather_app.model.WeatherRespDTO;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.ui.Model;

@EnableWebMvc
@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
    private final DynamoDBMapper dynamoDBMapper;
	
	
	public WeatherController(DynamoDBMapper dynamoDBMapper) {
	    	
		    this.dynamoDBMapper = dynamoDBMapper;
	    }

	@Value("${api.key}")
	private String apikey;

//---------------------------------------------------------

// ===============================Get Data Returned web and save data===================================

	/*
	//public ResponseEntity<String> getCityWeather(@RequestParam("city") String city, Model model) throws IOException {
	
	//throws JsonProcessingException
	
	@GetMapping("/weather")
	public String getCityWeather(@RequestParam("city") String city, Model model) {	

		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appId=" + apikey + "&units=metric";

		RestTemplate restTemplate = new RestTemplate();

		WeatherResp weatherResp = restTemplate.getForObject(url, WeatherResp.class);

		if (weatherResp != null) {

			model.addAttribute("id", weatherResp.getWeather().get(0).getId());
			model.addAttribute("city", weatherResp.getName());
			model.addAttribute("country", weatherResp.getSys().getCountry());
			model.addAttribute("weatherDescription", weatherResp.getWeather().get(0).getDescription());
			model.addAttribute("temperature", weatherResp.getMain().getTemp());
			model.addAttribute("humidity", weatherResp.getMain().getHumidity());
			model.addAttribute("windSpeed", weatherResp.getWind().getSpeed());
			String weatherIcon = "wi wi-owm-" + weatherResp.getWeather().get(0).getId();
			model.addAttribute("weatherIcon", weatherIcon);

		} else {
			model.addAttribute("error", "Location Not Found !!");
		}

//		ObjectMapper mapper = new ObjectMapper();
//		String json = mapper.writeValueAsString(model);

		WeatherRespDTO weatherRespDTO = new WeatherRespDTO();

		weatherRespDTO.setId(weatherResp.getWeather().get(0).getId());
		weatherRespDTO.setName(weatherResp.getName());
		weatherRespDTO.setCountry(weatherResp.getSys().getCountry());
		weatherRespDTO.setTemperature(weatherResp.getMain().getTemp());
		weatherRespDTO.setHumidity(weatherResp.getMain().getHumidity());
		weatherRespDTO.setWeatherDescription(weatherResp.getWeather().get(0).getDescription());
		weatherRespDTO.setWindSpeed(weatherResp.getWind().getSpeed());

		System.out.println("Json:" + model);
		System.out.println("New_Json:" + weatherRespDTO);

		// this.dynamoDBMapper.save(weatherRespDTO);
		System.out.println("successfully created weatherResponse" + weatherRespDTO.toString());

		//return ResponseEntity.ok().body("");
		return "weather";

	}
}

*/

	
	
//--------------------------------------- Correct Running with Path_variable no Web -------------------------------------			


  
  @GetMapping("/{city}") public String saveCityWeather(@PathVariable("city")
  String city,Model model) throws IOException { 
  String url = "https://api.openweathermap.org/data/2.5/weather?q=" +city+ "&appId=" +
  apikey + "&units=metric";
  
  RestTemplate restTemplate = new RestTemplate();
  
  
  WeatherResp weatherResp = restTemplate.getForObject(url, WeatherResp.class);
  
  if(weatherResp != null) {
  
  model.addAttribute("id", weatherResp.getWeather().get(0).getId());
  model.addAttribute("city",weatherResp.getName());
  model.addAttribute("humidity",weatherResp.getMain().getHumidity());
  model.addAttribute("windSpeed",weatherResp.getWind().getSpeed());
  
  model.addAttribute("country",weatherResp.getSys().getCountry());
  model.addAttribute("weatherDescription",weatherResp.getWeather().get(0).
  getDescription());
  model.addAttribute("temperature",weatherResp.getMain().getTemp());
  
  
  }else { model.addAttribute("error","Location Not Found !!"); }
  
  ObjectMapper mapper = new ObjectMapper(); String json =
 mapper.writeValueAsString(model);
  
  WeatherRespDTO weatherRespDTO = new WeatherRespDTO();
  
  weatherRespDTO.setId(weatherResp.getWeather().get(0).getId());
  weatherRespDTO.setName(weatherResp.getName());
  weatherRespDTO.setHumidity(weatherResp.getMain().getHumidity());
  weatherRespDTO.setWindSpeed(weatherResp.getWind().getSpeed());
  
  weatherRespDTO.setCountry(weatherResp.getSys().getCountry());
  weatherRespDTO.setTemperature(weatherResp.getMain().getTemp());
  weatherRespDTO.setWeatherDescription(weatherResp.getWeather().get(0).
  getDescription());
  
  
  System.out.println("Json:"+ json); System.out.println("New_Json:"+
  weatherRespDTO);
  
  
  this.dynamoDBMapper.save(weatherRespDTO);
  System.out.println("successfully created weatherResponse" +
  weatherRespDTO.toString());
  
  return "successfully created weatherResponse for "+city+" : "+json;
  
  }
  
  }
 

