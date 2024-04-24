package com.example.demo;

import com.example.demo.rest.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ServiceTests {
    private WebClient.Builder builder;
    @Test
    public void testGetGeolocation() {
        WebClient.Builder builder = WebClient.builder()
                .baseUrl("http://api.openweathermap.org");


        GeolocationService geolocationService = new GeolocationService(builder);
        Geolocation[] result = geolocationService.getGeolocation("London");

        Geolocation one_place = result[0];
        assertNotNull(one_place.getLat());
        assertNotNull(one_place.getLon());
        assertNotNull(one_place.getCountry());
        assertNotNull(one_place.getState());
        assertNotNull(one_place.getName());

        LocalNames local = one_place.getLocalNames();

    }

    @Test
    public void testWeatherService() {
        WebClient.Builder builder = WebClient.builder()
                .baseUrl("http://api.openweathermap.org");

        double[] test = {40.0154155, -105.270241};
        String units = "imperial";
        WeatherService weatherService = new WeatherService(builder);

        WeatherData result = weatherService.getWeatherData(test, units);
        assertNotNull(result.getLat());
        assertNotNull(result.getLon());
        assertNotNull(result.getTimezone());
        assertNotNull(result.getTimezoneOffset());

        Current currentData = result.getCurrent();
        assertNotNull(currentData.getPressure());
        assertNotNull(currentData.getDt());
        assertNotNull(currentData.getSunrise());
        assertNotNull(currentData.getSunset());
        assertNotNull(currentData.getTemp());
        assertNotNull(currentData.getUvi());
        assertNotNull(currentData.getClouds());
        assertNotNull(currentData.getHumidity());
        assertNotNull(currentData.getDewPoint());
        assertNotNull(currentData.getFeelsLike());
        assertNotNull(currentData.getVisibility());
        assertNotNull(currentData.getWindDeg());
        assertNotNull(currentData.getWindSpeed());



        Weather weatherInfo = currentData.getWeather().get(0);
        assertNotNull(weatherInfo.getIcon());
        assertNotNull(weatherInfo.getId());
        assertNotNull(weatherInfo.getDescription());
        assertNotNull(weatherInfo.getMain());

    }
}
