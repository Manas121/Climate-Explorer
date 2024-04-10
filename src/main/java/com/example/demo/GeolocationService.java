package com.example.demo;


import com.example.demo.rest.Geolocation;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeolocationService {

    private final WebClient webClient;

    public GeolocationService(WebClient.Builder builder) {

        webClient = builder.baseUrl("http://api.openweathermap.org/geo/1.0/").build();

    }


    public Geolocation[] getGeolocation () {


        return webClient
                .get()
                .uri("/direct?q=London&limit=5&appid=5235d709fb93256bbfcd63adb95f4fad")
                .retrieve()
                .bodyToMono(Geolocation[].class).block();


    }

}
