package ptt.app.weatherandroid.models.manager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ptt.app.weatherandroid.models.entity.CityCodeObject;

public class SearchManager {

    private ArrayList<CityCodeObject> cityList;
    private SearchManagerListener callback;

    public SearchManager(Context context){
        cityList = new CityCodeManager(context).getList();
    }

    public void setListener(SearchManagerListener callback){
        this.callback = callback;
    }

    private List<CityCodeObject> cityQuery(String name){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return cityList.stream().filter(
                    detail -> detail.getName().toLowerCase().contains(name.toLowerCase())
            ).collect(Collectors.toList());
        }else{
            List<CityCodeObject> callbackList = new ArrayList<>();
            for(CityCodeObject itemFilter : cityList){
                if(itemFilter.getName().toLowerCase().contains(name)){
                    callbackList.add(itemFilter);
                }
            }
            return callbackList;
        }
    }

    public void onQueryTextSubmit(String text){
        callback.onQueryCitySuccess(cityQuery(text));
    }

    // -----------------------------  interface

    public interface SearchManagerListener {
        void onQueryCitySuccess(List<CityCodeObject> city);
    }
}
