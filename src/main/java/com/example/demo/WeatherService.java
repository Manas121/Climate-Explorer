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
    String units = "metric";
    private final WebClient webClient;

    public WeatherService(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://api.openweathermap.org").build();
    }

    public WeatherData getWeatherData(double[] lat_lon, String unit) {
        if (!Objects.equals(unit, "")){
            units = unit;
        }
        String url = "/data/3.0/onecall?lat=" + lat_lon[0] + "&lon=" + lat_lon[1] + "&units="+units+"&exclude=minutely,hourly,daily,alerts&appid=578f5e2d2109b29226d0b74c71c4eabe";
        String result = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WeatherData weatherData = objectMapper.readValue(result, WeatherData.class);
            Current currentWeather = weatherData.getCurrent();

            System.out.println("TEST OUTPUT: Current Temperature: " + currentWeather.getTemp());
            System.out.println("Weather Description: " + currentWeather.getWeather().get(0).getDescription());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherData.class)
                .block();
    }
}
