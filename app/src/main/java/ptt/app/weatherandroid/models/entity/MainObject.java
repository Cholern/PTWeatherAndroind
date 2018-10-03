package ptt.app.weatherandroid.models.entity;

import com.google.gson.annotations.SerializedName;

public class MainObject {
    @SerializedName("temp")
    public double temp;

    @SerializedName("pressure")
    public double pressure;

    @SerializedName("humidity")
    public double humidity;

    @SerializedName("temp_min")
    public double temp_min;

    @SerializedName("temp_max")
    public double temp_max;
}
