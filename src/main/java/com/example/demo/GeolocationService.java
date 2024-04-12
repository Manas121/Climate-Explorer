package com.example.demo;


import com.example.demo.rest.Current;
import com.example.demo.rest.Geolocation;
import com.example.demo.rest.WeatherData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeolocationService {

    private static GeolocationService instance;
    private final WebClient webClient;

    public GeolocationService() {
        this.webClient = WebClient.builder().baseUrl("http://api.openweathermap.org").build();
    }

    public static GeolocationService getInstance() {
        if (instance == null) {
            instance = new GeolocationService();
        }
        return instance;
    }


    public Geolocation[] getGeolocation (String location) {
        String url = "/geo/1.0/direct?q="+location+"&limit=5&appid=578f5e2d2109b29226d0b74c71c4eabe";

        String result = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return webClient
                .get()
                .uri(url)// This API Key will change
                .retrieve()
                .bodyToMono(Geolocation[].class).block();

    }

}
