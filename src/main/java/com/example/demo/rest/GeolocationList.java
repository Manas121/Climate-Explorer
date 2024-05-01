package com.example.demo.rest;

import com.example.demo.GeolocationService;
import com.example.demo.ServiceFactory;
import com.example.demo.WeatherService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Route("/geo")
public class GeolocationList extends VerticalLayout implements IList {

    public GeolocationList(){
        displayList();
    }

    // geo Implementation for strategy class
    public void displayList (){
        // Use of singleton
        GeolocationService serviceGeo = ServiceFactory.createGeolocationService();
        WeatherService serviceWeather = ServiceFactory.createWeatherService();

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
        grid.removeAllColumns();
        grid.addColumn(Geolocation::getName).setHeader("Name");
        grid.addColumn(Geolocation::getLat).setHeader("Latitude");
        grid.addColumn(Geolocation::getLon).setHeader("Longitude");
        grid.addColumn(Geolocation::getCountry).setHeader("Country");
        grid.addColumn(Geolocation::getState).setHeader("State");

        search.addClickListener(event -> {
            String location = field.getValue();
            grid.removeAllColumns();
            grid.addColumn(Geolocation::getName).setHeader("Name");
            grid.addColumn(Geolocation::getLat).setHeader("Latitude");
            grid.addColumn(Geolocation::getLon).setHeader("Longitude");
            grid.addColumn(Geolocation::getCountry).setHeader("Country");
            grid.addColumn(Geolocation::getState).setHeader("State");

            Geolocation[] geo = serviceGeo.getGeolocation((location));

            grid.setItems(geo);

            var gridCurrent = new Grid<>(Current.class);
            var gridWeather = new Grid<>(Weather.class);
            var icon = new Image();

            grid.addComponentColumn(geolocation -> {
                Button button = new Button("View Weather");
                button.addClickListener(e -> {
                    remove(icon);
                    remove(gridWeather);
                    remove(gridCurrent);

                    double[] weatherInput = new double[2];

                    weatherInput[0] = geolocation.getLat();
                    weatherInput[1] = geolocation.getLon();

                    Current currentWeather = serviceWeather.getWeatherData(weatherInput, "imperial").getCurrent();
                    currentWeather.setDt(convertTime(currentWeather.getDt()));
                    currentWeather.setSunrise(convertTime(currentWeather.getSunrise()));
                    currentWeather.setSunset(convertTime(currentWeather.getSunset()));

                    gridCurrent.setItems(currentWeather);
                    gridCurrent.setColumns("dt","temp","feelsLike","clouds","humidity","pressure","visibility","uvi","windSpeed","sunrise","sunset");
                    gridCurrent.setMaxHeight("100px");

                    List<Weather> weatherInfo = currentWeather.getWeather();
                    icon.setSrc("https://openweathermap.org/img/wn/"+weatherInfo.get(0).getIcon()+".png");
                    icon.setHeight("50px");
                    icon.setWidth("50px");

                    gridWeather.setItems(weatherInfo);
                    gridWeather.setColumns("main", "description");
                    gridWeather.setMaxHeight("100px");

                    add(icon);
                    add(gridWeather);
                    add(gridCurrent);

                });
                return button;
            }).setHeader("More");

//            grid.setColumns("name", "lat", "lon", "country", "state", "More");
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

    public Integer convertTime(Integer utc) {
        LocalDateTime time_utc = LocalDateTime.ofEpochSecond(utc, 0, ZoneOffset.UTC);
        int hour = time_utc.getHour();
        int minutes = time_utc.getMinute();
        return hour*100 + minutes;
    }


}

