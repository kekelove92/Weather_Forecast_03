package com.framgia.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.weather.R;
import com.framgia.weather.model.week.WeekData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by TungAnh on 5/8/17.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<WeekData> mData;
    private LayoutInflater mInflater;
    DateFormat format2;

    public WeatherAdapter(Context context,
                          ArrayList<WeekData> data) {
        mContext = context;
        mData = data;
        mInflater = (LayoutInflater) mContext
            .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        format2 = new SimpleDateFormat("EEEE");
    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {


        WeekData weekData = mData.get(position);
        holder.mTextTempMax.setText((int)(weekData.getTempMin())+ "");
        holder.mTextTempMin.setText((int)(weekData.getTempMax())+ "");
        if(position == 0){
            holder.mTextDay.setText(R.string.today);
        }else{
            holder.mTextDay.setText(format2.format(weekData.getDay()) + "");
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextDay;
        private TextView mTextTempMax;
        private TextView mTextTempMin;

        public ViewHolder(final View itemView) {
            super(itemView);
            mTextDay = (TextView) itemView.findViewById(R.id.tv_day);
            mTextTempMax = (TextView) itemView.findViewById(R.id.text_tempMax);
            mTextTempMin = (TextView) itemView.findViewById(R.id.text_tempMin);
        }
    }
    public void refersh(ArrayList<WeekData> alist){
        mData = alist;
        notifyDataSetChanged();
    }
}
