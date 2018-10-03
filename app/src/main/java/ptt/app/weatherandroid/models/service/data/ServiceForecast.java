package ptt.app.weatherandroid.models.service.data;

import android.os.AsyncTask;

import com.google.gson.Gson;

import ptt.app.weatherandroid.models.entity.ForecastObject;
import ptt.app.weatherandroid.models.service.network.FetchWeather;


public class ServiceForecast extends FetchWeather {
    private static onResultCallback callback;

    public ServiceForecast(String url) {
        super(url);
    }

    public void get(final onResultCallback _callback){
        callback = _callback;
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected void onFetchSuccess(String jsonString) {
        if (callback == null) return;
        try {
            Gson gson = new Gson();
            ForecastObject forecastObject = gson.fromJson(jsonString, ForecastObject.class);
            if(forecastObject != null){
                callback.onFetchSuccess(forecastObject);
            }else {
                callback.onFailed();
            }
        }catch (Exception e){
            e.printStackTrace();
            callback.onFailed();
        }finally {
            callback = null;
        }
    }

    @Override
    protected void onFetchFailed() {

    }

    public interface onResultCallback {
        void onFetchSuccess(ForecastObject forecastObject);
        void onFailed();
    }
}