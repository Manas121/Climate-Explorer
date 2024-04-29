package com.example.demo.rest;

import com.example.demo.GeolocationService;
import com.example.demo.ServiceFactory;
import com.example.demo.WeatherService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/geo")
public class GeolocationList extends VerticalLayout {
    public GeolocationList (){
        GeolocationService serviceGeo = ServiceFactory.createGeolocationService();

        TextField field = new TextField();
        field.setLabel("Enter location");
        field.setHelperText("Helper text");
        field.setPlaceholder("Enter location");
        field.setTooltipText("Tooltip text");
        field.setClearButtonVisible(true);
        field.setPrefixComponent(VaadinIcon.VAADIN_H.create());
        field.setSuffixComponent(new Span(":)"));
        Button search = new Button("Search");

        var grid = new Grid <Geolocation>(Geolocation.class);

        search.addClickListener(event -> {
            String location = field.getValue();

            grid.setItems(serviceGeo.getGeolocation(location));

            grid.setColumns("name","lat","lon","country","state");
        });

        add(field);
        add(search);
        add(grid);


//        double weatherInput[] = new double[2];
//        Geolocation[] geo = serviceGeo.getGeolocation(location);
//
//        weatherInput[0] = geo[0].getLat();
//        weatherInput[1] = geo[0].getLon();
    }


}

