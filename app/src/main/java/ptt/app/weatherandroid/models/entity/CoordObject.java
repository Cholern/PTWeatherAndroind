package ptt.app.weatherandroid.models.entity;

import com.google.gson.annotations.SerializedName;

public class CoordObject {
    @SerializedName("lon")
    public double lon;

    @SerializedName("lat")
    public double lat;
}
