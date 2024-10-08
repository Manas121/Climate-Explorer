package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.rest.WeatherData;
import com.example.demo.rest.Current;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;


@Service
public class WeatherService {
    private String units = "metric";


    //Decorator pattern
    private final LoggingWebClientDecorator webClient;

    // Builder pattern
    public WeatherService() {
        WebClient.Builder builder = WebClient.builder();
        WebClient originalWebClient = builder.baseUrl("http://api.openweathermap.org").build();
        this.webClient = new LoggingWebClientDecorator(originalWebClient);
    }

    public WeatherData getWeatherData(double[] lat_lon, String unit) {
        String result = webClient
                .getWeatherServiceWebClient()
                .uri("/data/3.0/onecall?lat=" + lat_lon[0] + "&lon=" + lat_lon[1] + "&units=" + units + "&exclude=minutely,hourly,daily,alerts&appid=578f5e2d2109b29226d0b74c71c4eabe")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            WeatherData weatherData = objectMapper.readValue(result, WeatherData.class);
            Current currentWeather = weatherData.getCurrent();

            if (weatherData != null) {
                printWeatherData(currentWeather);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        // Use the decorated webClient to make requests. Ex:
        return webClient.
                getWeatherServiceWebClient()
                .uri("/data/3.0/onecall?lat=" + lat_lon[0] + "&lon=" + lat_lon[1] + "&units=" + units + "&exclude=minutely,hourly,daily,alerts&appid=578f5e2d2109b29226d0b74c71c4eabe")
                .retrieve()
                .bodyToMono(WeatherData.class)
                .block();
    }

    private void printWeatherData(Current currentWeather) {
        if (currentWeather != null) {
            System.out.println("Current Temperature: " + currentWeather.getTemp());
            System.out.println("Weather Description: " + currentWeather.getWeather().get(0).getDescription());
        }
    }
}
