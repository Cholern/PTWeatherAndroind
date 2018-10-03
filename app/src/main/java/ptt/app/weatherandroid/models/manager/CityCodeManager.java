package ptt.app.weatherandroid.models.manager;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import ptt.app.weatherandroid.models.entity.CityCodeObject;

public class CityCodeManager {

    private Context context;
    private static ArrayList<CityCodeObject> cityCode = null;

    CityCodeManager(Context context){
        this.context = context;
    }

    public ArrayList<CityCodeObject> getList() {
        if(cityCode != null)
            return cityCode;

        try{
            String jsonString = loadJSONFromAsset();
            cityCode = getHistoryArray(jsonString);
            return cityCode;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void setList(){
        try{
            String jsonString = loadJSONFromAsset();
            cityCode = getHistoryArray(jsonString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<CityCodeObject> getHistoryArray(String jsonString){
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<CityCodeObject>>(){}.getType();
        return gson.fromJson(jsonString, collectionType);
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = context.getAssets().open("city_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int read = is.read(buffer);
            Log.d("InputStream","read : "+read);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
