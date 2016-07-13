package org.fuzzyrobot.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by neil on 13/07/2016.
 */
public class Main implements Parcelable {
    public static final float ABSOLUTE_ZERO = 273.15f;
    private float temp;

    public float getTempC() {
        return temp - ABSOLUTE_ZERO;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(temp);
    }

    public Main() {
    }

    protected Main(Parcel in) {
        temp = in.readFloat();
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel source) {
            return new Main(source);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };
}
