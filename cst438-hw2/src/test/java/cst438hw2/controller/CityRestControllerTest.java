package cst438hw2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438hw2.domain.City;
import cst438hw2.domain.CityInfo;
import cst438hw2.domain.Country;
import cst438hw2.domain.TempAndTime;
import cst438hw2.service.CityService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

	// Initializations for tests
	@MockBean
	CityService cityService;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<CityInfo> jsonCityAttempt;
	
	@BeforeEach
	public void setUpEach() {
		MockitoAnnotations.openMocks(this);
		JacksonTester.initFields(this, new ObjectMapper());
	}
	// Tests run below
	
	@Test
	public void test1() throws Exception {
		
		// Create the test case CityInfo arguments
		Country country = new Country("TST", "testCountry");
		City testCity = new City(1, "TestCity", "TestDistrict", "TST", 10000);
		TempAndTime testTempTime = new TempAndTime(280, "1620759333", -10000);
		
		given(cityService.getCityInfo("TestCity")).willReturn(new CityInfo(testCity, 
				country, testTempTime));
		
		// Run a simulated server response
		MockHttpServletResponse response = mvc.perform(get("/api/cities/TestCity")).andReturn()
				.getResponse();
		
		// Ensure response is 200 OK
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// Try to convert returned JSON strings back into CityInfo object
		CityInfo cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		CityInfo expectedResult = new CityInfo(testCity, country, testTempTime);
		/* These conversions occur in another class, so they shouldn't be necessary for testing
		 * the controller:
		 *    expectedResult.tempTime.temp = 44.3;
		 *    expectedResult.tempTime.time = "2:55";
		 */

		// Compare to actual result
		assertThat(cityResult).isEqualTo(expectedResult);
		
	}
	
	// Second test supplies invalid city name for path and will fail
	@Test
	public void test2() throws Exception {
		// Create the test case CityInfo arguments
		Country country = new Country("TST", "testCountry");
		// Here the 
		City testCity = new City(1, "TestCity", "TestDistrict", "TST", 10000);
		TempAndTime testTempTime = new TempAndTime(280, "1620759333", -10000);
		
		given(cityService.getCityInfo("TestCity")).willReturn(new CityInfo(testCity, 
				country, testTempTime));
		
		// Run a simulated server response
		// This one has an invalid city name (Doesn't match our sample of 1 existing city)
		MockHttpServletResponse response = mvc.perform(get("/api/cities/RestCity")).andReturn()
				.getResponse();
		
		// Ensure response is 200 OK
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// Try to convert returned JSON strings back into CityInfo object
		CityInfo cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		//City expectedTestCity = new City(1, "TestCity", "TestDistrict", "TST", 1000);
		CityInfo expectedResult = new CityInfo(testCity, country, testTempTime);

		// Compare to actual result
		assertThat(cityResult).isEqualTo(expectedResult);
	}
}
