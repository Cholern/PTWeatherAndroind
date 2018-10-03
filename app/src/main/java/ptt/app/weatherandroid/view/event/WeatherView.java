package ptt.app.weatherandroid.view.event;

import android.content.Context;

import java.util.List;

import ptt.app.weatherandroid.models.entity.CityObject;
import ptt.app.weatherandroid.models.entity.ForecastObject;
import ptt.app.weatherandroid.models.entity.WeatherDetailObject;

public interface WeatherView {
    void weatherDataChange(CityObject city, List<WeatherDetailObject> list);
    void listForecastDataChange(ForecastObject list);
    void onViewRefresh();
    Context getContext();
}
