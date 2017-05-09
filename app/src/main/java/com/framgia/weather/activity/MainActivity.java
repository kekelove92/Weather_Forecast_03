package com.framgia.weather.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.weather.R;
import com.framgia.weather.adapter.WeatherAdapter;
import com.framgia.weather.gps.GPSTracker;
import com.framgia.weather.model.day.CurrentMain;
import com.framgia.weather.model.day.SOAnswersResponse;
import com.framgia.weather.model.week.ListMain;
import com.framgia.weather.model.week.SOAnswersWeekResponse;
import com.framgia.weather.model.week.WeekData;
import com.framgia.weather.utils.ApiUtils;
import com.framgia.weather.utils.ConvertTemperature;
import com.framgia.weather.utils.SOService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {
    int REQUEST_PLACE_PICKER = 1;
    private static final String APP_ID = "0634efb262d9398a09fc2693c5120589";
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private TextView mTemp;
    private PlacePicker.IntentBuilder builder;
    private TextView mHumidity;
    private TextView mWinds;
    private TextView mTempMax;
    private TextView mTempMin;
    private TextView mCity;
    private ImageView mImage_addLocation;
    private ImageView mImage_setting;
    private GPSTracker mGps;
    private RecyclerView mRecyclerView;
    private SOService mService;
    private String mLatitude;
    private String mLongitude;
    private int cnt = 8;
    private ArrayList<WeekData> mListWeekMain;
    private Context mcontext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private WeatherAdapter mAdapter;
    private boolean temperatureC = true;
    private ConvertTemperature convert;
    public CurrentMain mCurrentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getSOService();
        initView();
        builder = new PlacePicker.IntentBuilder();
        setAdapter();
        gpsOn();
        loadAnswersCurrentDay(mLatitude, mLongitude, APP_ID);
        loadAnswersWeekDay(mLatitude, mLongitude, APP_ID);
    }

    public void setAdapter() {
        mListWeekMain = new ArrayList<>();
        mAdapter = new WeatherAdapter(this, mListWeekMain);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void gpsOn() {
        mGps = new GPSTracker(this);
        mLongitude = String.valueOf(mGps.getLongitude());
        mLatitude = String.valueOf(mGps.getLatitude());
    }

    public void initView() {
        mTemp = (TextView) findViewById(R.id.text_tempMain);
        mHumidity = (TextView) findViewById(R.id.text_humidity);
        mTempMax = (TextView) findViewById(R.id.text_tempMax);
        mTempMin = (TextView) findViewById(R.id.text_tempMin);
        mCity = (TextView) findViewById(R.id.text_City);
        mWinds = (TextView) findViewById(R.id.text_winds);
        mImage_addLocation = (ImageView) findViewById(R.id.image_add);
        mImage_addLocation.setOnClickListener(this);
        mImage_setting = (ImageView) findViewById(R.id.image_setting);
        mImage_setting.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvTemperature);
        pullToRefresh();
    }

    public void pullToRefresh() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mGps.getLocation();
                mLongitude = String.valueOf(mGps.getLongitude());
                mLatitude = String.valueOf(mGps.getLatitude());
                loadAnswersCurrentDay(mLatitude, mLongitude, APP_ID);
                loadAnswersWeekDay(mLatitude, mLongitude, APP_ID);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void loadAnswersCurrentDay(String lat, String lon, String appid) {
        mService.getAnswersCurrentDay(lat, lon, appid).enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call,
                                   Response<SOAnswersResponse> response) {
                if (response.isSuccessful()) {
                    mCurrentMain = response.body().getCurrentMain();
                    if (temperatureC) {
                        Double tempmin = mCurrentMain.getTempMin();
                        mCurrentMain.setTempMin(KelvinToC(tempmin));
                        Double tempmax = mCurrentMain.getTempMax();
                        mCurrentMain.setTempMax(KelvinToC(tempmax));
                        Double temp = mCurrentMain.getTemp();
                        mCurrentMain.setTemp(KelvinToC(temp));
                    } else {
                        Double tempmin = mCurrentMain.getTempMin();
                        mCurrentMain.setTempMin(KelvinToF(tempmin));
                        Double tempmax = mCurrentMain.getTempMax();
                        mCurrentMain.setTempMax(KelvinToF(tempmax));
                        Double temp = mCurrentMain.getTemp();
                        mCurrentMain.setTemp(KelvinToF(temp));
                    }
                    changeValues(response.body());
                } else {
                    Toast.makeText(MainActivity.this, R.string.NotLoadApiday, Toast.LENGTH_SHORT)
                        .show();
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.FaileLoadAPI, Toast.LENGTH_SHORT)
                    .show();
            }
        });
    }

    public void loadAnswersWeekDay(String lat, String lon, String appid) {
        mService.getAnswersWeekDay(lat, lon, cnt, appid).enqueue(new Callback<SOAnswersWeekResponse>
            () {
            @Override
            public void onResponse(Call<SOAnswersWeekResponse> call,
                                   Response<SOAnswersWeekResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        mListWeekMain = new ArrayList<WeekData>();
                        for (int i = 1; i < response.body().getListMain().size(); i++) {
                            ListMain a = response.body().getListMain().get(i);
                            long l = Long.valueOf(a.getDt());
                            Date date = new Date(l * 1000L);
                            mListWeekMain.add(new WeekData(a.getWeekMain().getTempMax(), a
                                .getWeekMain().getTempMin(), date));
                        }
                        if (temperatureC) {
                            for (int i = 0; i < mListWeekMain.size(); i++) {
                                double tempmax = mListWeekMain.get(i).getTempMax();
                                mListWeekMain.get(i).setTempMax(KelvinToC(tempmax));
                                double tempmin = mListWeekMain.get(i).getTempMin();
                                mListWeekMain.get(i).setTempMin(KelvinToC(tempmin));
                            }
                        } else {
                            for (int i = 0; i < mListWeekMain.size(); i++) {
                                double tempmax = mListWeekMain.get(i).getTempMax();
                                mListWeekMain.get(i).setTempMax(KelvinToF(tempmax));
                                double tempmin = mListWeekMain.get(i).getTempMin();
                                mListWeekMain.get(i).setTempMin(KelvinToF(tempmin));
                            }
                        }
                        mAdapter.refersh(mListWeekMain);
                    } catch (Exception e) {
                    }
                } else {
                    Toast.makeText(MainActivity.this, R.string.NotLoadApiWeek, Toast.LENGTH_SHORT)
                        .show();
                }
            }

            @Override
            public void onFailure(Call<SOAnswersWeekResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.FailLoadApiWeek, Toast.LENGTH_SHORT)
                    .show();
            }
        });
    }

    public void changeValues(SOAnswersResponse item) {
        mTemp.setText((int) item.getCurrentMain().getTemp() + "");
        mTempMax.setText(((int) item.getCurrentMain().getTempMax()) + "");
        mTempMin.setText(((int) item.getCurrentMain().getTempMin()) + "");
        mHumidity.setText(item.getCurrentMain().getHumidity() + "");
        mWinds.setText((int) item.getCurrentWind().getSpeed() + "");
        mCity.setText(item.getName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PLACE_PICKER) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(MainActivity.this, data);
                LatLng latLong = place.getLatLng();
                mCity.setText(place.getName());
                String lat = latLong.latitude + "";
                String lon = latLong.longitude + "";
                loadAnswersCurrentDay(lat, lon, APP_ID);
                loadAnswersWeekDay(lat, lon, APP_ID);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_add:
                try {
                    Intent intent =
                        new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
                    startActivityForResult(intent, 1);
                } catch (GooglePlayServicesRepairableException e) {
                } catch (GooglePlayServicesNotAvailableException e) {
                }
                break;
            case R.id.image_setting:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle(R.string.selectTemp);
                RadioGroup rdGroup = (RadioGroup) dialog.findViewById(R.id.grDialog);
                RadioButton f = (RadioButton) rdGroup.findViewById(R.id.doF);
                RadioButton c = (RadioButton) rdGroup.findViewById(R.id.doC);
                if (temperatureC) {
                    c.setChecked(true);
                } else {
                    f.setChecked(true);
                }
                rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        switch (checkedId) {
                            case R.id.doC:
                                if (!temperatureC) {
                                    doido(true);
                                    temperatureC = true;
                                }
                                break;
                            case R.id.doF:
                                if (temperatureC) {
                                    doido(false);
                                    temperatureC = false;
                                }
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    private void doido(boolean cCheck) {
        double t = 0, t2 = 0, t3 = 0;
        if (cCheck) {
            t = convert.convertFahrenheitToCelcius(mCurrentMain.getTemp());
            t2 = convert.convertFahrenheitToCelcius(mCurrentMain.getTempMax());
            t3 = convert.convertFahrenheitToCelcius(mCurrentMain.getTempMin());
            for (int i = 0; i < mListWeekMain.size(); i++) {
                Double TempMaxi = mListWeekMain.get(i).getTempMax();
                mListWeekMain.get(i).setTempMax(convert.convertFahrenheitToCelcius(TempMaxi));
                Double TempMini = mListWeekMain.get(i).getTempMin();
                mListWeekMain.get(i).setTempMin(convert.convertFahrenheitToCelcius(TempMini));
            }
        } else {
            t = convert.convertCelciusToFahrenheit(mCurrentMain.getTemp());
            t2 = convert.convertCelciusToFahrenheit(mCurrentMain.getTempMax());
            t3 = convert.convertCelciusToFahrenheit(mCurrentMain.getTempMin());
            for (int i = 0; i < mListWeekMain.size(); i++) {
                Double TempMaxi = mListWeekMain.get(i).getTempMax();
                mListWeekMain.get(i).setTempMax(convert.convertCelciusToFahrenheit(TempMaxi));
                Double TempMini = mListWeekMain.get(i).getTempMin();
                mListWeekMain.get(i).setTempMin(convert.convertCelciusToFahrenheit(TempMini));
            }
        }
        mAdapter.refersh(mListWeekMain);
        mCurrentMain.setTemp(t);
        mCurrentMain.setTempMax(t2);
        mCurrentMain.setTempMin(t3);
        mTemp.setText((int) t + "");
        mTempMax.setText((int) t2 + "");
        mTempMin.setText((int) t3 + "");
    }

    public double KelvinToC(double value) {
        return value - 273.15;
    }

    public double KelvinToF(double value) {
        return (value * 9 / 5 - 459.67);
    }
}
