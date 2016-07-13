package org.fuzzyrobot.weather.api;

import org.fuzzyrobot.weather.model.City;
import org.fuzzyrobot.weather.model.Item;

import java.util.List;

/**
 * Created by neil on 12/07/2016.
 */
public class WeatherResponse {

    private City city;
    private List<Item> list;

    public City getCity() {
        return city;
    }

    public List<Item> getList() {
        return list;
    }


    @Override
    public String toString() {
        return "WeatherResponse{" +
                "city=" + city +
                ", list=" + list +
                '}';
    }
}
