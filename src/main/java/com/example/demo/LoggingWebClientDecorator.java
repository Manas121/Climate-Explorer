package com.example.demo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.rest.WeatherData;
import com.example.demo.rest.Current;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
public class LoggingWebClientDecorator {

    private final WebClient delegate;

    public LoggingWebClientDecorator(WebClient delegate) {
        this.delegate = delegate;
    }

    public WebClient.RequestHeadersUriSpec<?> getWeatherServiceWebClient() {
        // Add logging functionality here
        System.out.println("Logging: Making Weather GET request");
        return delegate.get();
    }

    public WebClient.RequestHeadersUriSpec <?> getGeolocationServiceWebClient(){

        // Add logging functionality here
        System.out.println("Logging: Making Weather GET request");
        return delegate.get();

    }


}
