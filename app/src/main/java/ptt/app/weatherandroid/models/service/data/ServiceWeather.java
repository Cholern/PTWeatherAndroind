package ptt.app.weatherandroid.models.service.data;


import android.os.AsyncTask;
import com.google.gson.Gson;

import ptt.app.weatherandroid.models.entity.CityObject;
import ptt.app.weatherandroid.models.service.network.FetchWeather;


public class ServiceWeather extends FetchWeather {
    private static onResultCallback callback;

    public ServiceWeather(String url) {
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
            CityObject cityObject = gson.fromJson(jsonString, CityObject.class);
            if(cityObject != null){
                callback.onFetchSuccess(cityObject);
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
        void onFetchSuccess(CityObject cityObject);
        void onFailed();
    }
}
