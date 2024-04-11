package com.example.demo;


import com.example.demo.rest.Geolocation;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeolocationService {

    private final WebClient webClient;

    public GeolocationService(WebClient.Builder builder) {

        webClient = builder.baseUrl("http://api.openweathermap.org").build();

    }


    public Geolocation[] getGeolocation (String location) {
        String url = "/geo/1.0/direct?q="+location+"&limit=5&appid=578f5e2d2109b29226d0b74c71c4eabe";

        return webClient
                .get()
                .uri(url)// This API Key will change
                .retrieve()
                .bodyToMono(Geolocation[].class).block();

    }

}
