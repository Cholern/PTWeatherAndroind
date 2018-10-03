package ptt.app.weatherandroid.models.entity;

public class WeatherDetailObject {

    private String title;
    private String value;

    public WeatherDetailObject(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}
