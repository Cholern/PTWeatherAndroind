package ptt.app.weatherandroid.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.models.entity.CityObject;
import ptt.app.weatherandroid.view.holder.ForecastViewHolder;

public class ForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<CityObject> items;

    public ForecastAdapter(){
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ForecastViewHolder forecastViewHolder = (ForecastViewHolder) holder;
        forecastViewHolder.setDetailView(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size() > 8 ? 8 : items.size();
    }

    public void setItems(List<CityObject> items){
        this.items = items;
    }
}
