package ptt.app.weatherandroid.presenter;

import java.util.List;

import ptt.app.weatherandroid.models.entity.CityCodeObject;
import ptt.app.weatherandroid.models.manager.SearchManager;
import ptt.app.weatherandroid.view.event.SearchingView;

public class SearchPresenter implements SearchManager.SearchManagerListener {

    private SearchingView view;
    private SearchManager searchManager;

    public SearchPresenter(SearchingView view){
        this.view = view;
        searchManager = new SearchManager(view.getContext());
        searchManager.setListener(this);
    }

    public void onQueryTextSubmit(String text){
        searchManager.onQueryTextSubmit(text);
    }

    @Override
    public void onQueryCitySuccess(List<CityCodeObject> city) {
        view.onDataChange(city);
    }
}
