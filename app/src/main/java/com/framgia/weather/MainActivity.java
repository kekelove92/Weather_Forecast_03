package com.framgia.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.weather.model.day.SOAnswersResponse;
import com.framgia.weather.utils.ApiUtils;
import com.framgia.weather.utils.SOService;
import com.framgia.weather.gps.GPSTracker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String APPID = "0634efb262d9398a09fc2693c5120589";
    private TextView mTemp;
    private TextView mHumidity;
    private TextView mWinds;
    private TextView mTempMax;
    private TextView mTempMin;
    private ImageView btn_addLocation;
    private ImageView btn_setting;
    private GPSTracker gps;
    private RecyclerView mRecyclerView;
    private SOService mService;
    private String mLatitude;
    private String mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getSOService();
        mRecyclerView = (RecyclerView) findViewById(R.id.rvTemperature);
        addControls();
        addEvents();
        gps = new GPSTracker(this);
        mLongitude = String.valueOf(gps.getLongitude());
        mLatitude = String.valueOf(gps.getLatitude());
        loadAnswersCurrentDay(mLatitude, mLongitude, APPID);
    }

    public void loadAnswersCurrentDay(String lat, String lon, String appid) {
        mService.getAnswersCurrentDay(lat, lon, appid).enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call,
                                   Response<SOAnswersResponse> response) {
                if (response.isSuccessful()) {
                    changeValues(response.body());
                } else {
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
            }
        });
    }

    public void getAnswersWeekDay(String lat, String lon, String appid) {
        mService.getAnswersWeekDay(lat, lon, appid).enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call,
                                   Response<SOAnswersResponse> response) {
                if (response.isSuccessful()) {
                } else {
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
            }
        });
    }

    public void addEvents() {
        btn_addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void addControls() {
        mTemp = (TextView) findViewById(R.id.text_tempMain);
        mHumidity = (TextView) findViewById(R.id.text_humidity);
        mTempMax = (TextView) findViewById(R.id.text_tempMax);
        mTempMin = (TextView) findViewById(R.id.text_tempMin);
        mWinds = (TextView) findViewById(R.id.text_winds);
        btn_addLocation = (ImageView) findViewById(R.id.image_add);
        btn_setting = (ImageView) findViewById(R.id.image_setting);
    }

    public void changeValues(SOAnswersResponse item) {
        mTemp.setText((int) (item.getCurrentMain().getTemp() - 273) + "˚C");
        mHumidity.setText(item.getCurrentMain().getHumidity() + "%");
        mTempMax.setText((int) (item.getCurrentMain().getTempMax() - 273) + "˚C");
        mTempMin.setText((int) (item.getCurrentMain().getTempMin() - 273) + "˚C");
        mWinds.setText(item.getWind().getSpeed() + "km/s");
    }
}
