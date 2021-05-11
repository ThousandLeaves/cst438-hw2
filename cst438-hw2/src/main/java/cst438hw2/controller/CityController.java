package cst438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cst438hw2.service.CityService;
import cst438hw2.domain.CityInfo;

// Handles http get requests for project
@Controller
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities/{city}")
	public String getCityInfo(@PathVariable("city") String cityName, Model model) {
		System.out.println(cityName);
		CityInfo cityInfo = cityService.getCityInfo(cityName);
		model.addAttribute("cityInfo", cityInfo);
		return "result";
	}
	
}
