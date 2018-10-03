package ptt.app.weatherandroid.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.models.entity.WeatherDetailObject;
import ptt.app.weatherandroid.view.holder.WeatherViewHolder;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<WeatherDetailObject> items;

    public WeatherAdapter(){
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WeatherViewHolder weatherViewHolder = (WeatherViewHolder) holder;
        weatherViewHolder.setViewDetail(items.get(position));
    }

    @Override
    public int getItemCount() {
        return  items.size();
    }

    public void setItems(List<WeatherDetailObject> list){
        this.items = list;
    }
}
