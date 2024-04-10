package com.example.demo.rest;

import com.example.demo.GeolocationService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/geo")
public class GeolocationList extends VerticalLayout {

    public GeolocationList (GeolocationService service){

        var grid = new Grid <Geolocation>(Geolocation.class);
        grid.setItems(service.getGeolocation());
        add(grid);


    }


}