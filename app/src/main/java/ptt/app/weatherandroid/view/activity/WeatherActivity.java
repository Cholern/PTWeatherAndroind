package ptt.app.weatherandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.models.entity.CityObject;
import ptt.app.weatherandroid.models.entity.ForecastObject;
import ptt.app.weatherandroid.models.entity.WeatherDetailObject;
import ptt.app.weatherandroid.presenter.WeatherPresenter;
import ptt.app.weatherandroid.view.adapter.ForecastAdapter;
import ptt.app.weatherandroid.view.adapter.WeatherAdapter;
import ptt.app.weatherandroid.view.event.WeatherView;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    final static public int REQUEST_ACCESS_FINE_LOCATION = 1;
    final private String SEARCH_MENU = "SEARCH_MENU";

    private WeatherPresenter weatherPresenter;
    private ForecastAdapter forecastAdapter;
    private WeatherAdapter weatherAdapter;
    private TextView weatherTextView;
    private CollapsingToolbarLayout toolbarLayout;
    private FrameLayout toggleContain;

    @Override
    protected void onResume(){
        super.onResume();
        if(weatherPresenter != null){
            weatherPresenter.resume();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setupView();

        weatherPresenter = new WeatherPresenter(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ACCESS_FINE_LOCATION:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    weatherPresenter.fetchWithLocation();
                }
                break;
        }
    }

    private void setupView(){
        toolbarLayout = findViewById(R.id.toolbar_layout);
        weatherTextView = findViewById(R.id.weatherTextView);
        toggleContain = findViewById(R.id.toggleContain);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        RecyclerView forecastRecyclerView = findViewById(R.id.forecastRecyclerView);
        RecyclerView weatherRecyclerView = findViewById(R.id.weatherRecyclerView);
        Toolbar toolbar = findViewById(R.id.toolbar);

        forecastAdapter = new ForecastAdapter();
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        forecastRecyclerView.setAdapter(forecastAdapter);

        weatherAdapter = new WeatherAdapter();
        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        weatherRecyclerView.setAdapter(weatherAdapter);

        toggleButton.setOnCheckedChangeListener((compoundButton, b) -> weatherPresenter.onToggleButtonChange(b));
        toggleContain.setVisibility(View.GONE);

        toolbar.setTitle(getResources().getString(R.string.title_find));
        setSupportActionBar(toolbar);
    }

    private void toSearchScreen(){
        Intent intent = new Intent(this, SearchActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void weatherDataChange(CityObject city, List<WeatherDetailObject> list) {
        weatherTextView.setText(weatherPresenter.getTemp(city.main.temp));
        toolbarLayout.setTitle(city.name);
        weatherAdapter.setItems(list);
        weatherAdapter.notifyDataSetChanged();
        toggleContain.setVisibility(View.VISIBLE);
    }

    @Override
    public void listForecastDataChange(ForecastObject forecastObject) {
        forecastAdapter.setItems(forecastObject.list);
        forecastAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewRefresh() {
        weatherTextView.setText(weatherPresenter.refreshTemp());
        forecastAdapter.notifyDataSetChanged();
        weatherAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, 0, 0, SEARCH_MENU).setIcon(R.drawable.search_icon)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals(SEARCH_MENU)){
            toSearchScreen();
        }
        return super.onOptionsItemSelected(item);
    }
}
