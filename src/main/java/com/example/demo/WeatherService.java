package com.example.demo;

import com.example.demo.rest.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class WeatherService {
    private final WebClient webClient;

    public WeatherService(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://api.openweathermap.org").build();
    }

    public WeatherData[] getWeatherData (double[] lat_lon) { // We need to get the lat/lon from getGeoLocation and pass that into getting the weather data
        return webClient
                .get()
                .uri("/data/3.0/onecall?lat="+lat_lon[0]+"&lon="+lat_lon[1]+"&appid=578f5e2d2109b29226d0b74c71c4eabe") // also change api key
                .retrieve()
                .bodyToMono(WeatherData[].class).block(); // CHANGE some other kind of display?
    }
}
