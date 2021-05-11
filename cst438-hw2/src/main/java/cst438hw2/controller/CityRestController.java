package cst438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cst438hw2.domain.*;
import cst438hw2.service.CityService;

// Returns API details (JSON string), works with CityService & WeatherService classes
// Data is fetched from CityService
// JSON is passed + utilized by CityController to generate a webpage
@RestController
public class CityRestController {

	@Autowired
	private CityService cityService;
	
	@GetMapping("/api/cities/{city}")
	public CityInfo getWeather(@PathVariable("city") String cityName) {
		CityInfo cityInfo = cityService.getCityInfo(cityName);
		return cityInfo;		
	}
	
}
