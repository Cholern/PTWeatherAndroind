package ptt.app.weatherandroid.models.entity;

import com.google.gson.annotations.SerializedName;

public class WindObject {
    @SerializedName("speed")
    public double speed;

    @SerializedName("deg")
    public double deg;
}
