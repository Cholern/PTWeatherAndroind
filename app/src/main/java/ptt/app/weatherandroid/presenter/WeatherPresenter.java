package ptt.app.weatherandroid.presenter;

import android.annotation.SuppressLint;

import java.util.List;
import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.config.AppStorage;
import ptt.app.weatherandroid.models.entity.CityObject;
import ptt.app.weatherandroid.models.entity.ForecastObject;
import ptt.app.weatherandroid.models.entity.WeatherDetailObject;
import ptt.app.weatherandroid.models.manager.CurrentCityManager;
import ptt.app.weatherandroid.models.manager.WeatherManager;
import ptt.app.weatherandroid.view.event.WeatherView;

public class WeatherPresenter implements WeatherManager.WeatherManagerListener {

    private WeatherManager weatherManage;
    private WeatherView view;
    private double tempValue;

    public WeatherPresenter(WeatherView view){
        this.view = view;
        weatherManage = new WeatherManager(view.getContext());
        weatherManage.setListener(this);
    }

    public void fetchWithLocation() {
        weatherManage.fetchWithLocation();
    }

    @SuppressLint("DefaultLocale")
    public String getTemp(double value){
        tempValue = value;
        if(new AppStorage().isCelsius()){
            return view.getContext().getResources().getString(R.string.temp_c, String.valueOf(value));
        }else{
            value = (value * 1.8) + 32;
            return view.getContext().getResources().getString(R.string.temp_f, String.format("%.1f", value));
        }
    }

    public String refreshTemp(){
        return getTemp(tempValue);
    }

    public void resume(){
        long newId = new CurrentCityManager().getId();
        if(weatherManage.getCurrentId() != newId && newId > 0){
            weatherManage.fetchWeather(newId);
        }
    }

    public void onToggleButtonChange(boolean b){
        weatherManage.onToggleChange(b);
    }

    @Override
    public void onWeatherListReceived(CityObject city, List<WeatherDetailObject> list) {
        view.weatherDataChange(city, list);
    }

    @Override
    public void onForecastListReceived(ForecastObject city) {
        view.listForecastDataChange(city);
    }

    @Override
    public void onViewRefresh() {
        view.onViewRefresh();
    }

    @Override
    public void onFetchFailed() {

    }
}
