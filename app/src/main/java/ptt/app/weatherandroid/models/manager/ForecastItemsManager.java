package ptt.app.weatherandroid.models.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.List;

import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.config.AppStorage;
import ptt.app.weatherandroid.models.entity.WeatherObject;

public class ForecastItemsManager {

    private Context context;

    public ForecastItemsManager(Context context){
        this.context = context;
    }

    public Drawable getImageWithCode(List<WeatherObject> weather){
        if(weather.size() > 0){
            try {
                String drawableName = context.getResources().getString(R.string.drawable_name, weather.get(0).icon);
                return context.getResources().getDrawable(context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return context.getResources().getDrawable(R.drawable.icon50d);
    }

    @SuppressLint("DefaultLocale")
    public String getTemp(double value){
        if(new AppStorage().isCelsius()){
            return context.getResources().getString(R.string.temp_c, String.valueOf(value));
        }else{
            value = (value * 1.8) + 32;
            return context.getResources().getString(R.string.temp_f, String.format("%.1f", value));
        }
    }

    public String getTimeWithTextDt(String dt_text){
        String[] split = dt_text.split(" ");
        String time = split[split.length - 1];
        return time.substring(0, time.length() > 5 ? 5 : time.length());
    }

    public String getWin(double win){
        return context.getResources().getString(R.string.win_text, String.valueOf(win));
    }
}
