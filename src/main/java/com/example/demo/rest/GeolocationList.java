package com.example.demo.rest;

import com.example.demo.GeolocationService;
import com.example.demo.WeatherService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/geo")
public class GeolocationList extends VerticalLayout {

    public GeolocationList (GeolocationService service){
        String location = "Boulder";
        var grid = new Grid <Geolocation>(Geolocation.class);
        grid.setItems(service.getGeolocation(location));
        grid.setColumns("name","lat","lon","country","state");
        add(grid);

        double weatherInput[] = new double[2];
        Geolocation[] geo = service.getGeolocation(location);
        weatherInput[0] = geo[0].getLat();
        weatherInput[1] = geo[0].getLon();
        // add something to link this to weather api



    }


}