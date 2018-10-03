package ptt.app.weatherandroid.models.service.network;


import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class FetchWeather extends AsyncTask<Void, Void, String> {
    private String url;
    //private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected FetchWeather(String url){
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient client = new OkHttpClient();
        Request request;
        try {
            request = new Request.Builder()
                    // .addHeader(this.headerVerify, this.headerPartner)
                    .url(this.url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            onFetchFailed();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String jsonString){
        onFetchSuccess(jsonString);
    }
    protected abstract void onFetchSuccess(String jsonString);
    protected abstract void onFetchFailed();

}