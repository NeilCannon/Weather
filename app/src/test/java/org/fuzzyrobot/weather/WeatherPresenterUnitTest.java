package org.fuzzyrobot.weather;

import android.location.Location;

import com.trello.rxlifecycle.FragmentLifecycleProvider;

import org.fuzzyrobot.weather.api.WeatherResponse;
import org.fuzzyrobot.weather.api.WeatherService;
import org.fuzzyrobot.weather.model.City;
import org.fuzzyrobot.weather.model.Item;
import org.fuzzyrobot.weather.presenter.WeatherPresenter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.TestScheduler;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class WeatherPresenterUnitTest {
    @Test
    public void test() throws Exception {

        final TestScheduler testScheduler = new TestScheduler();
        final WeatherService weatherService = mock(WeatherService.class);
        WeatherPresenter presenter = new WeatherPresenter() {

            @Override
            protected WeatherService getWeatherService() {
                return weatherService;
            }

            @Override
            protected Scheduler getScheduler() {
                return testScheduler;
            }
        };
        final WeatherPresenter.WeatherView weatherView = mock(WeatherPresenter.WeatherView.class);
        final FragmentLifecycleProvider lifecycleProvider = mock(FragmentLifecycleProvider.class);
        final Location location = mock(Location.class);

        final WeatherResponse weatherResponse = new WeatherResponse(new City("London"), new ArrayList<Item>());
        given(weatherService.getForecast("0.0", "0.0")).willReturn(Observable.just(weatherResponse));
        given(lifecycleProvider.bindToLifecycle()).willAnswer(new IdentityTransformer());

        presenter.update(weatherView, location, lifecycleProvider);

        // move the scheduler on
        Thread.sleep(200L);
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        verify(weatherView, atLeastOnce()).setCityName("London");
    }

}