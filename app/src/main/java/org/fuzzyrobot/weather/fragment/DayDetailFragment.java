package org.fuzzyrobot.weather.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.fuzzyrobot.weather.R;
import org.fuzzyrobot.weather.adapter.DayReadingAdapter;
import org.fuzzyrobot.weather.model.Day;
import org.joda.time.format.DateTimeFormat;

import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DayDetailFragment extends BaseFragment {

    public static final String ARG_DAY = "DAY";
    @InjectView(R.id.readings_list)
    protected RecyclerView recyclerView;

    public static DayDetailFragment create(Day day) {
        DayDetailFragment fragment = new DayDetailFragment();
        final Bundle args = new Bundle();
        args.putParcelable(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView dateText = (TextView) view.findViewById(R.id.date);
        final Day day = getArguments().getParcelable(ARG_DAY);
        dateText.setText(day.getDate().toString(DateTimeFormat.forPattern("E")));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new DayReadingAdapter(this, day));
    }

}
