package ptt.app.weatherandroid.models.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityObject {
    @SerializedName("coord")
    public CoordObject coord;

    @SerializedName("weather")
    public List<WeatherObject> weatherList;

    @SerializedName("base")
    public String base;

    @SerializedName("main")
    public MainObject main;

    @SerializedName("wind")
    public WindObject wind;

    @SerializedName("clouds")
    public CloudsObject clouds;

    @SerializedName("dt")
    public int dt;

    @SerializedName("dt_txt")
    public String dt_txt;

    @SerializedName("sys")
    public SysObject sys;

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("cod")
    public int cod;
}
