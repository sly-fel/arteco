package com.arteco.arteco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weatherAPI.WeatherForecast;

import java.io.IOException;

@SpringBootApplication
public class ArtecoApplication {

	public static void main(String[] args) throws IOException {SpringApplication.run(ArtecoApplication.class, args);
		WeatherForecast weatherForecast = new WeatherForecast();
		weatherForecast.showWeatherForecast();
	}

}
