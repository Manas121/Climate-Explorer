package com.example.demo.rest;

import com.example.demo.WeatherService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/weather")
public class WeatherDataList extends VerticalLayout{
    double[] test = {51.5073219,-0.1276474};
    String units = "imperial";
    public WeatherDataList (WeatherService service){
        var grid = new Grid <Current>(Current.class);
        Current currentWeather = service.getWeatherData(test, units).getCurrent();
        grid.setItems(currentWeather);
        grid.setColumns("temp","feelsLike","clouds","humidity","pressure","visibility","uvi","windSpeed","sunrise","sunset");
        add(grid);
    }
}
