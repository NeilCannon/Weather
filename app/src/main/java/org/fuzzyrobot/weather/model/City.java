package org.fuzzyrobot.weather.model;

import android.support.annotation.VisibleForTesting;

/**
 * Created by neil on 13/07/2016.
 */
public class City {

    public City() {
    }

    @VisibleForTesting
    public City(final String name) {
        this.name = name;
    }

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
