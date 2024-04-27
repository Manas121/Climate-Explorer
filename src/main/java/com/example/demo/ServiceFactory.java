package com.example.demo;


import org.springframework.web.reactive.function.client.WebClient;
public class ServiceFactory {

    // Logic to create and configure services
    public static WeatherService createWeatherService() {
        return new WeatherService();
    }

    public static GeolocationService createGeolocationService() {
        return new GeolocationService();
    }


}
