package ptt.app.weatherandroid.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.models.entity.CityCodeObject;
import ptt.app.weatherandroid.view.event.CellClickListener;
import ptt.app.weatherandroid.view.holder.SearchViewHolder;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CityCodeObject> items;
    private CellClickListener cellListener;

    public SearchAdapter(){
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
        searchViewHolder.setCityName(items.get(position).getName());
        searchViewHolder.contentView.setOnClickListener(view -> onContentViewClickListener(position));
    }

    private void onContentViewClickListener(int index){
        cellListener.onCellClick(items.get(index).getId());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void setListener(CellClickListener cellListener){
        this.cellListener = cellListener;
    }

    public void setItems(List<CityCodeObject> items){
        this.items = items;
    }
}
