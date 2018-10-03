package ptt.app.weatherandroid.models.entity;

import com.google.gson.annotations.SerializedName;

public class WeatherObject {
    @SerializedName("id")
    public int id;

    @SerializedName("main")
    public String main;

    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;

}
