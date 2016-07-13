package org.fuzzyrobot.weather.fragment;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.fuzzyrobot.weather.R;
import org.fuzzyrobot.weather.model.Day;
import org.fuzzyrobot.weather.presenter.WeatherPresenter;

import java.util.List;

import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends BaseFragment implements WeatherPresenter.WeatherView {

    private static final String LOCATION = "LOCATION";
    @InjectView(R.id.days_pager)
    protected ViewPager daysPager;

    @InjectView(R.id.city_name)
    protected TextView cityNameTv;

    private Location location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            location = savedInstanceState.getParcelable(LOCATION);
        }
        update();
    }

    public void setLocation(final Location location) {
        this.location = location;
        update();
    }

    private void update() {
        if (daysPager == null || location == null) {
            return;
        }
        new WeatherPresenter().update(this, location, this);
    }

    public void setCityName(final String cityName) {
        cityNameTv.setText(cityName);
    }

    @Override
    public void setDays(final List<Day> days) {
        daysPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(final int position) {
                final Day thisDay = days.get(position);
                return DayDetailFragment.create(thisDay);
            }

            @Override
            public int getCount() {
                return days.size();
            }
        });
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LOCATION, location);
    }
}
