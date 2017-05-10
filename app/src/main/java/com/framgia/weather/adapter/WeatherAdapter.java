package com.framgia.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.weather.R;

/**
 * Created by TungAnh on 5/8/17.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context mContext;
//    private ArrayList<Weather> mData;
    private LayoutInflater mInflater;

    //To-Do  adapter
//    public WeatherAdapter(Context context,
//                          ArrayList<Weather> data) {
//        mContext = context;
//        mData = data;
//        mInflater = LayoutInflater.from(mContext);
//    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {
//         TO-DO set adapter on layout
//        Weather weather = mData.get(position);
//        holder.mDay.setText(weather.getDay());
//        holder.mTempMin.setText(weather.getTempMin());
//        holder.mTempMin.setText(weather.getTempMax());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mDay;
        private TextView mTempMax;
        private TextView mTempMin;

        public ViewHolder(final View itemView) {
            super(itemView);
            mDay = (TextView) itemView.findViewById(R.id.tv_day);
            mTempMax = (TextView) itemView.findViewById(R.id.tv_tempMax);
            mTempMin = (TextView) itemView.findViewById(R.id.tv_tempMin);
        }
    }
}
