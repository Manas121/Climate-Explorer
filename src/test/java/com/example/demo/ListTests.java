package com.example.demo;

import com.example.demo.rest.GeolocationList;
import com.example.demo.rest.WeatherData;
import com.example.demo.rest.WeatherDataList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class ListTests {
    @Test
    public void testWeatherDataList(){
        WebClient.Builder builder = WebClient.builder()
                .baseUrl("http://api.openweathermap.org");

        WeatherDataList weatherDataList = new WeatherDataList();

    }

    @Test
    public void testGeoLocationList(){
        WebClient.Builder builder = WebClient.builder()
                .baseUrl("http://api.openweathermap.org");

        GeolocationList geoList = new GeolocationList();
    }
}
