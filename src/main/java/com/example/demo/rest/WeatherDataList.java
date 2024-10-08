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

@Route("/weather")
public class WeatherDataList extends VerticalLayout implements IList{
    String units = "imperial";

    public WeatherDataList() {
        displayList();
    }

    // Weather Implementation for strategy class
    //Usage of singleton pattern - service and serviceGeo are single instances of WeatherService and GeolocationService, invoking the use of singleton pattern
    public void displayList (){

        WeatherService serviceWeather = ServiceFactory.createWeatherService();
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
        var gridCurrent = new Grid <Current>(Current.class);
        var gridWeather = new Grid<Weather>(Weather.class);
        var icon = new Image();
        search.addClickListener(event -> {
            String location = field.getValue();
            Geolocation[] geo = serviceGeo.getGeolocation(location);
            double[] weatherInput = new double[2];

            // Returns data for the most popular location matching input
            weatherInput[0] = geo[0].getLat();
            weatherInput[1] = geo[0].getLon();

            Current currentWeather = serviceWeather.getWeatherData(weatherInput, units).getCurrent();
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
        });

        //add(icon);
        add(field);
        add(search);
        add(icon);
        add(gridWeather);
        add(gridCurrent);
    }

    public Integer convertTime(Integer utc) {
        LocalDateTime time_utc = LocalDateTime.ofEpochSecond(utc, 0, ZoneOffset.UTC);
        int hour = time_utc.getHour();
        int minutes = time_utc.getMinute();
        return hour*100 + minutes;
    }
}
