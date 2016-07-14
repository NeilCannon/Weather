package org.fuzzyrobot.weather.presenter;

import android.location.Location;
import android.support.annotation.VisibleForTesting;

import com.trello.rxlifecycle.FragmentLifecycleProvider;

import org.fuzzyrobot.weather.api.ApiModule;
import org.fuzzyrobot.weather.api.WeatherResponse;
import org.fuzzyrobot.weather.api.WeatherService;
import org.fuzzyrobot.weather.model.Day;
import org.fuzzyrobot.weather.model.Item;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by neil on 13/07/2016.
 */
public class WeatherPresenter {

    public interface WeatherView {
        void setCityName(String cityName);

        void setDays(List<Day> days);
    }

    public void update(final WeatherView weatherView, final Location location, FragmentLifecycleProvider lifecycleProvider) {
        final Observable<WeatherResponse> weatherResponseObservable = getWeatherService().getForecast(String.valueOf(location.getLatitude()),
                String.valueOf(location.getLongitude()));

        weatherResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(getScheduler())
                .compose(lifecycleProvider.<WeatherResponse>bindToLifecycle())
                .subscribe(new Action1<WeatherResponse>() {
                    @Override
                    public void call(final WeatherResponse weatherResponse) {

                        weatherView.setCityName(weatherResponse.getCity().getName());
                        LocalDate lastDate = null;
                        final List<Day> days = new ArrayList<>();
                        Day day = null;
                        for (Item item : weatherResponse.getList()) {
                            LocalDate date = item.getLocalDate();

                            if (lastDate == null || !lastDate.equals(date)) {
                                day = new Day(date);
                                days.add(day);
                            }
                            day.addReading(item);
                            lastDate = date;
                        }
                        weatherView.setDays(days);

                    }
                });


    }

    @VisibleForTesting
    protected WeatherService getWeatherService() {
        return new ApiModule().getWeatherService();
    }

    @VisibleForTesting
    protected Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
