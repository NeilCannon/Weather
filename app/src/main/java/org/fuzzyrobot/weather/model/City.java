package org.fuzzyrobot.weather.model;

/**
 * Created by neil on 13/07/2016.
 */
public class City {
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}
