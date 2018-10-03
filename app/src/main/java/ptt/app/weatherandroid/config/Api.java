package ptt.app.weatherandroid.config;

public class Api {

    public String getForecastApi(long cityId, String appid){
        return "http://api.openweathermap.org/data/2.5/forecast?id="+cityId+"&APPID="+appid+"&units=metric";
    }

    public String getWeathertApi(long cityId, String appid){
        return "http://api.openweathermap.org/data/2.5/weather?id="+cityId+"&APPID="+appid+"&units=metric";
    }

    public String getWeatherWithLatLon(double lat, double lon, String appid){
        return "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&APPID="+appid+"&units=metric";
    }
}
