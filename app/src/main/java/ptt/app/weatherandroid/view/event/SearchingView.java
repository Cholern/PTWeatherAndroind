package ptt.app.weatherandroid.view.event;

import android.content.Context;

import java.util.List;

import ptt.app.weatherandroid.models.entity.CityCodeObject;

public interface SearchingView {
    void onDataChange(List<CityCodeObject> city);
    Context getContext();
}
