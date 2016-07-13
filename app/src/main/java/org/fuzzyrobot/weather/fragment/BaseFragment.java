package org.fuzzyrobot.weather.fragment;

import android.os.Bundle;
import android.view.View;

import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.fuzzyrobot.weather.application.WeatherApplication;

import butterknife.ButterKnife;

/**
 * Created by neil on 12/07/2016.
 */
public abstract class BaseFragment extends RxFragment {

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    // So LeakCanary can watch fragments
    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = WeatherApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}