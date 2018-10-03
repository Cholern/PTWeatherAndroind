package ptt.app.weatherandroid.models.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastObject {
    @SerializedName("cod")
    public int cod;

    @SerializedName("message")
    public double message;

    @SerializedName("cnt")
    public int cnt;

    @SerializedName("list")
    public List<CityObject> list;

    @SerializedName("city")
    public CityObject city;
}
