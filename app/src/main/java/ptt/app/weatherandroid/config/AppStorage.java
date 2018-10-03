package ptt.app.weatherandroid.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class AppStorage {

    private static final String MAIN_STORAGE = "WEATHER_APP";
    private static final String CITY_ID_STORAGE = "CITY_ID_STORAGE";

    private static boolean isC = true;

    private SharedPreferences prefs;
    private Context context;

    public AppStorage(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(MAIN_STORAGE, MODE_PRIVATE);
    }

    public AppStorage(){

    }

    public void setC(boolean c){
        isC = c;
    }

    public boolean isCelsius(){
        return isC;
    }

    public void setId(long id){
        Log.d("112x","set id "+id);
        SharedPreferences.Editor editor = context.getSharedPreferences(MAIN_STORAGE, MODE_PRIVATE).edit();
        editor.putLong(CITY_ID_STORAGE, id);
        editor.apply();
    }

    public long getId(){
        return prefs.getLong(CITY_ID_STORAGE, -1);
    }

}
