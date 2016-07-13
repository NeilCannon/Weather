package org.fuzzyrobot.weather.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by neil on 13/07/2016.
 */
public class Item implements Parcelable {
    private static final long MILLIS_IN_SECOND = 1000L;
    private long dt;
    private Main main;
    private List<Weather> weather;

    public LocalDate getLocalDate() {
        final DateTime dateTime = getLocalDateTime();
        return dateTime.toLocalDate();
    }

    public LocalTime getLocalTime() {
        final DateTime dateTime = getLocalDateTime();
        return dateTime.toLocalTime();
    }

    @NonNull
    private DateTime getLocalDateTime() {
        DateTime original = new DateTime(dt * MILLIS_IN_SECOND);
        DateTimeZone tmd = DateTimeZone.forTimeZone(TimeZone.getDefault());
        return new DateTime(original, tmd);
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    @Override
    public String toString() {
        return "\nItem{" +
                "dt=" + dt +
                ", main=" + main +
                ", weather=" + weather +
                ", getDate()=" + getLocalDate() +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dt);
        dest.writeParcelable(main, flags);
        dest.writeList(weather);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        dt = in.readLong();
        main = in.readParcelable(Main.class.getClassLoader());
        weather = new ArrayList<Weather>();
        in.readList(weather, Weather.class.getClassLoader());
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
