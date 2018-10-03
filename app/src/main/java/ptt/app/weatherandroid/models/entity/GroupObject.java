package ptt.app.weatherandroid.models.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupObject {

    @SerializedName("cnt")
    public int cnt;

    @SerializedName("list")
    public List<CityObject> cities;
}
