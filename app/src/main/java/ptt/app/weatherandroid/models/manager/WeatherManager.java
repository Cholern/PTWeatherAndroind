package ptt.app.weatherandroid.models.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.config.Api;
import ptt.app.weatherandroid.config.AppStorage;
import ptt.app.weatherandroid.models.entity.CityObject;
import ptt.app.weatherandroid.models.entity.ForecastObject;
import ptt.app.weatherandroid.models.entity.WeatherDetailObject;
import ptt.app.weatherandroid.models.service.data.ServiceForecast;
import ptt.app.weatherandroid.models.service.data.ServiceWeather;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static ptt.app.weatherandroid.view.activity.WeatherActivity.REQUEST_ACCESS_FINE_LOCATION;

public class WeatherManager {

    private Context context;
    private long currentId;
    private WeatherManagerListener callback;
    private AppStorage appStorage;

    public WeatherManager(Context context){
        this.context = context;
        appStorage = new AppStorage(context);
        new CityCodeManager(context).setList();

        if(appStorage.getId() > 0){
            fetchWeather(appStorage.getId());
        }else{
            checkPermission();
        }
    }

    public void setListener(WeatherManagerListener callback){
        this.callback = callback;
    }

    @SuppressLint("MissingPermission")
    public void fetchWithLocation() {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert lm != null;
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null){
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            fetchWeatherWithLatLon(latitude, longitude);
        }
    }

    private void checkPermission() {
        int findPermissionCheck = ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION);
        int coarsePermissionCheck = ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION);
        if (findPermissionCheck != PackageManager.PERMISSION_GRANTED || coarsePermissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);
        } else {
            fetchWithLocation();
        }
    }

    public long getCurrentId() {
        return this.currentId;
    }

    private void fetchWeatherWithLatLon(double lat, double lon){
        String url = new Api().getWeatherWithLatLon(lat,lon,"ea574594b9d36ab688642d5fbeab847e");
        new ServiceWeather(url).get(new ServiceWeather.onResultCallback() {
            @Override
            public void onFetchSuccess(CityObject cityObject) {
                callback.onWeatherListReceived(cityObject, getWeatherDetailObject(cityObject));
                currentId = cityObject.id;
                fetchForecast(currentId);
            }

            @Override
            public void onFailed() {
                callback.onFetchFailed();
            }
        });
    }

    private void fetchForecast(long id){
        appStorage.setId(id);
        String url = new Api().getForecastApi(id,"ea574594b9d36ab688642d5fbeab847e");
        new ServiceForecast(url).get(new ServiceForecast.onResultCallback() {
            @Override
            public void onFetchSuccess(ForecastObject forecastObject) {
                callback.onForecastListReceived(forecastObject);
            }

            @Override
            public void onFailed() {
                callback.onFetchFailed();
            }
        });
    }

    public void fetchWeather(long id){
        currentId = id;
        String url = new Api().getWeathertApi(id,"ea574594b9d36ab688642d5fbeab847e");
        new ServiceWeather(url).get(new ServiceWeather.onResultCallback() {
            @Override
            public void onFetchSuccess(CityObject cityObject) {
                callback.onWeatherListReceived(cityObject, getWeatherDetailObject(cityObject));
                fetchForecast(id);
            }

            @Override
            public void onFailed() {
                callback.onFetchFailed();
            }
        });
    }

    public void onToggleChange(boolean b){ // true is F
        appStorage.setC(!b);
        callback.onViewRefresh();
    }

    private List<WeatherDetailObject>  getWeatherDetailObject(CityObject cityObject){
        List<WeatherDetailObject> detail = new ArrayList<>();
        if(cityObject.weatherList != null){
            detail.add(new WeatherDetailObject(context.getResources().getString(R.string.title_description),cityObject.weatherList.get(0).description));
        }
        detail.add(new WeatherDetailObject(context.getResources().getString(R.string.title_humidity), context.getResources().getString(R.string.value_humidity, String.valueOf(cityObject.main.humidity))));
        detail.add(new WeatherDetailObject(context.getResources().getString(R.string.title_pressure),String.valueOf(cityObject.main.pressure)));
        detail.add(new WeatherDetailObject(context.getResources().getString(R.string.title_win), context.getResources().getString(R.string.win_text, String.valueOf(cityObject.wind.speed))));
        return detail;
    }

    // ----------------------------------------------------------------- interface

    public interface WeatherManagerListener {
        void onWeatherListReceived(CityObject forecast, List<WeatherDetailObject> list);
        void onForecastListReceived(ForecastObject city);
        void onViewRefresh();
        void onFetchFailed();
    }
}
