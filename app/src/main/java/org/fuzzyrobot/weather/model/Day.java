package org.fuzzyrobot.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neil on 13/07/2016.
 */
public class Day implements Parcelable {
    private LocalDate date;
    private List<Item> readings = new ArrayList<>();

    public Day(final LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Item> getReadings() {
        return readings;
    }

    public boolean addReading(final Item object) {
        return readings.add(object);
    }

    public Day() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.date);
        dest.writeTypedList(this.readings);
    }

    protected Day(Parcel in) {
        this.date = (LocalDate) in.readSerializable();
        this.readings = in.createTypedArrayList(Item.CREATOR);
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel source) {
            return new Day(source);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };
}
