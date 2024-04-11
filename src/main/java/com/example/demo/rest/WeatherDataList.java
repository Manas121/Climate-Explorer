package com.example.demo.rest;

import com.example.demo.WeatherService;
import com.example.demo.rest.Current;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/weather")
public class WeatherDataList extends VerticalLayout{
    double[] test = {51.5073219,-0.1276474};
    public WeatherDataList (WeatherService service){
        var grid = new Grid <WeatherData>(WeatherData.class);
        grid.setItems(service.getWeatherData(test));
        add(grid);
    }
}
