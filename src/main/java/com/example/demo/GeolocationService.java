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


    private final LoggingWebClientDecorator webClient;

    public GeolocationService() {
        //builder pattern
        WebClient.Builder builder = WebClient.builder();
        WebClient originalWebClient = builder.baseUrl("http://api.openweathermap.org").build();
        this.webClient = new LoggingWebClientDecorator(originalWebClient);
    }




    public Geolocation[] getGeolocation (String location) {
        String url = "/geo/1.0/direct?q="+location+"&limit=5&appid=578f5e2d2109b29226d0b74c71c4eabe";

        String result = webClient
                .getGeolocationServiceWebClient()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return webClient
                .getGeolocationServiceWebClient()
                .uri(url)// This API Key will change
                .retrieve()
                .bodyToMono(Geolocation[].class).block();
    }

}
