package cst438hw2.service;

import java.util.Calendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import cst438hw2.domain.TempAndTime;

@Service
public class WeatherService {

	private static final Logger Log =
			LoggerFactory.getLogger(WeatherService.class);
	private RestTemplate restTemplate;
	private String weatherUrl;
	private String apiKey;
	
	public WeatherService(		// 1
			@Value("${weather.url}") final String weatherUrl,
			@Value("${weather.apiKey}") final String apiKey ) {
		this.restTemplate = new RestTemplate();
		this.weatherUrl = weatherUrl;
		this.apiKey = apiKey;
	}
	
	public TempAndTime getTempAndTime(String cityName) {
		ResponseEntity<JsonNode> response =
				restTemplate.getForEntity(weatherUrl + "?q=" + cityName + "&appid=" +
				apiKey,
				JsonNode.class);
		// Takes text from server and parses into JSON format
		JsonNode json = response.getBody();		// 2
		Log.info("Status code from weather server:" +
				response.getStatusCodeValue());
		// "temp" is nested inside "main" attribute
		double temp = json.get("main").get("temp").asDouble();
		
		// Convert to F
		temp = Math.round((((temp - 273.15) * 9) / 5) + 32);
		
		long time = json.get("dt").asLong();
		int timezone = json.get("timezone").asInt();
		
		// Place time in human-readable format
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time * 1000L);
		TimeZone tz = TimeZone.getTimeZone("UTC");
		tz.setRawOffset(timezone * 1000);
		cal.setTimeZone(tz);
		String convTime = (cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE)).toString();
		
		return new TempAndTime(temp, convTime, timezone);
	}
}
