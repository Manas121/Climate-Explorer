package com.example.demo;


import org.springframework.web.reactive.function.client.WebClient;
public class ServiceFactory {
    private static WeatherService weatherInstance;
    private static GeolocationService geoInstance;

    // Logic to create and configure services
    // singleton pattern - only one instance of the services
    public static WeatherService createWeatherService() {
        if (weatherInstance == null) {
            weatherInstance = new WeatherService();
        }
        return weatherInstance;
    }

    public static GeolocationService createGeolocationService() {
        if (geoInstance == null) {
            geoInstance = new GeolocationService();
        }
        return geoInstance;
    }


}
