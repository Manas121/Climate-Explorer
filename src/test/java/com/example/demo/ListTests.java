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
        WeatherService weatherService = new WeatherService(builder);

        double[] test = {40.0154155, -105.270241};
        String units = "imperial";

        WeatherDataList weatherDataList = new WeatherDataList(weatherService);
    }

    @Test
    public void testGeoLocationList(){
        WebClient.Builder builder = WebClient.builder()
                .baseUrl("http://api.openweathermap.org");

        GeolocationService geoService = new GeolocationService();

        String location = "Boulder";
        GeolocationList geoList = new GeolocationList(geoService);
    }
}
