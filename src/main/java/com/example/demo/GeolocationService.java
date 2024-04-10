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


    public Geolocation[] getGeolocation () {

//         can we change this return a double array of lat/lon
        // or just have it store the values if we want to display the table too
        return webClient
                .get()
                .uri("/geo/1.0/direct?q=London&limit=5&appid=578f5e2d2109b29226d0b74c71c4eabe")// This API Key will change
                .retrieve()
                .bodyToMono(Geolocation[].class).block();

    }

    // would have to make a new return type
    public weather getWeatherData (double[] lat_lon) { // We need to get the lat/lon from getGeoLocation and pass that into getting the weather data
        return webClient
                .get()
                .uri("/data/3.0/onecall?lat="+lat_lon[0]+"&lon="+lat_lon[1]+"&appid=578f5e2d2109b29226d0b74c71c4eabe") // also change api key
                .retrieve()
                .bodyToMono(Geolocation[].class).block(); // CHANGE some other kind of display?
    }

}
