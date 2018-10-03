package ptt.app.weatherandroid.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.List;

import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.models.entity.CityCodeObject;
import ptt.app.weatherandroid.models.manager.CurrentCityManager;
import ptt.app.weatherandroid.presenter.SearchPresenter;
import ptt.app.weatherandroid.view.adapter.SearchAdapter;
import ptt.app.weatherandroid.view.event.CellClickListener;
import ptt.app.weatherandroid.view.event.SearchingView;


public class SearchActivity extends Activity implements SearchingView, CellClickListener {

    private SearchPresenter searchPresenter;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupView();

        searchPresenter = new SearchPresenter(this);
    }

    private void setupView(){
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.searchRecyclerView);
        setupListener();
    }

    private void setupListener(){
        searchView.setActivated(true);
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(onQueryTextListener);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter();
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            searchPresenter.onQueryTextSubmit(newText);
            return false;
        }
    };

    @Override
    public void onDataChange(List<CityCodeObject> city) {
        adapter.setItems(city);
        adapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onCellClick(long id) {
        new CurrentCityManager().setId(id);
        finish();
    }
}
