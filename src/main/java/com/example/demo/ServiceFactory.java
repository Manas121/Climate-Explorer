package com.example.demo;


import org.springframework.web.reactive.function.client.WebClient;
public class ServiceFactory {

    public static WeatherService createWeatherService() {
        // Logic to create and configure WeatherService instance

        return new WeatherService();

    }

    public static GeolocationService createGeolocationService() {
        // Logic to create and configure GeolocationService instance
        return new GeolocationService();

    }


}
