package org.fuzzyrobot.weather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.fuzzyrobot.weather.R;
import org.fuzzyrobot.weather.fragment.DayDetailFragment;
import org.fuzzyrobot.weather.model.Day;
import org.fuzzyrobot.weather.model.Item;
import org.joda.time.format.DateTimeFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by neil on 13/07/2016.
 */
public class DayReadingAdapter extends RecyclerView.Adapter<DayReadingAdapter.ViewHolder> {
    private DayDetailFragment dayDetailFragment;
    private final Day day;

    public DayReadingAdapter(final DayDetailFragment dayDetailFragment, final Day day) {
        this.dayDetailFragment = dayDetailFragment;
        this.day = day;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View inflate = LayoutInflater.from(dayDetailFragment.getActivity()).inflate(R.layout.item_reading, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item item = day.getReadings().get(position);
        holder.temp.setText(String.format("%.0fC", item.getMain().getTempC()));
        holder.time.setText(item.getLocalTime().toString(DateTimeFormat.shortTime()));
    }

    @Override
    public int getItemCount() {
        return day.getReadings().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.reading_time)
        TextView time;
        @InjectView(R.id.reading_temp)
        TextView temp;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
