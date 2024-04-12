package com.example.demo.rest;

import com.example.demo.WeatherService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.cglib.core.Local;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Route("/weather")
public class WeatherDataList extends VerticalLayout{
    double[] test = {40.0154155, -105.270241};
    String units = "imperial";
    public WeatherDataList (WeatherService service){
        var grid = new Grid <Current>(Current.class);
        Current currentWeather = service.getWeatherData(test, units).getCurrent();
        currentWeather.setDt(convertTime(currentWeather.getDt()));
        currentWeather.setSunrise(convertTime(currentWeather.getSunrise()));
        currentWeather.setSunset(convertTime(currentWeather.getSunset()));
        grid.setItems(currentWeather);
        grid.setColumns("dt","temp","feelsLike","clouds","humidity","pressure","visibility","uvi","windSpeed","sunrise","sunset");
        add(grid);
        grid.setMaxHeight("100px");

        List<Weather> weatherInfo = currentWeather.getWeather();
        Image icon = new Image("https://openweathermap.org/img/wn/"+weatherInfo.get(0).getIcon()+".png", "Weather Icon");
        add(icon);
        icon.setHeight("50px");
        icon.setWidth("50px");

        var grid1 = new Grid<Weather>(Weather.class);
        grid1.setItems(weatherInfo);
        grid1.setColumns("main", "description");
        grid1.setMaxHeight("100px");

        add(icon);
        add(grid1);
        add(grid);

    }

    public static Integer convertTime(Integer utc) {
        LocalDateTime time_utc = LocalDateTime.ofEpochSecond(utc, 0, ZoneOffset.UTC);
        int hour = time_utc.getHour();
        int minutes = time_utc.getMinute();
        return hour*100 + minutes;
    }
}
