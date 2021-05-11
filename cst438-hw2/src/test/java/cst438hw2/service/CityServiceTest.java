package cst438hw2.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cst438hw2.domain.City;
import cst438hw2.domain.CityInfo;
import cst438hw2.domain.CityRepository;
import cst438hw2.domain.Country;
import cst438hw2.domain.CountryRepository;
import cst438hw2.domain.TempAndTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CityServiceTest {

	// Initializations for testing
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;
	
	@MockBean
	private WeatherService weatherService;
	
	// Testing this class
	private CityService cs;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	// Test city service's ability to produce a CityInfo object
	@Test
	public void test1() {
		
		// Declared test data
		String cityName = "TestCity";
		Country country = new Country("TST", "testCountry");
		TempAndTime testTempTime = new TempAndTime(280, "1620759333", -10000);
		City testCity = new City(1, "TestCity", "TestDistrict", "TST", 10000);
		List<City> cities = new ArrayList<City>();
		cities.add(testCity);
		
		// Test returns from mocks
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		
		given(countryRepository.findByCode("TST")).willReturn(country);
		
		given(weatherService.getTempAndTime("TestCity")).willReturn(testTempTime);
		
		// Test expected result against test cityinfo
		cs = new CityService(cityRepository, countryRepository, weatherService);
		CityInfo expectedResult = new CityInfo(testCity, country, testTempTime);
		
		assertEquals(expectedResult,cs.getCityInfo(cityName));
	}
	
	// Test checks operations with multiple "same name" cities
	@Test
	public void test2() {
		
		// Declared test data
		String cityName = "TestCity";
		Country country = new Country("TST", "testCountry");
		TempAndTime testTempTime = new TempAndTime(280, "1620759333", -10000);
		City testCity = new City(1, "TestCity", "TestDistrict", "TST", 10000);
		City testCity2 = new City(2, "TestCity", "TestDistrict2", "TST2", 20000);
		List<City> cities = new ArrayList<City>();
		cities.add(testCity);
		cities.add(testCity2);
		
		// Test returns from mocks
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		
		given(countryRepository.findByCode("TST")).willReturn(country);
		
		given(weatherService.getTempAndTime("TestCity")).willReturn(testTempTime);
		
		// Test expected result against test cityinfo
		cs = new CityService(cityRepository, countryRepository, weatherService);
		CityInfo expectedResult = new CityInfo(testCity, country, testTempTime);
		assertEquals(expectedResult,cs.getCityInfo(cityName));
	}
	
}
