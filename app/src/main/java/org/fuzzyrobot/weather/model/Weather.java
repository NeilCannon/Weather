package org.fuzzyrobot.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by neil on 13/07/2016.
 */
public class Weather implements Parcelable {
    private String main;

    @Override
    public String toString() {
        return "Weather{" +
                "main='" + main + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(main);
    }

    public Weather() {
    }

    protected Weather(Parcel in) {
        main = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
}
