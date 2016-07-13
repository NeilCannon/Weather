package org.fuzzyrobot.weather.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by neil on 12/07/2016.
 */
public interface WeatherService {
    @GET("forecast?appid=755edfc3ddfb65cda1cc44b0068ed349")
    Observable<WeatherResponse> getForecast(@Query("lat") String lat, @Query("lon") String lon);

}
