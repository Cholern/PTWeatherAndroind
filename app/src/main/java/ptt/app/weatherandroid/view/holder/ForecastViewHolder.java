package ptt.app.weatherandroid.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.models.entity.CityObject;
import ptt.app.weatherandroid.models.manager.ForecastItemsManager;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    private ForecastItemsManager manager;
    private TextView timeTextView, tempTextView, winTextView;
    private ImageView icon;

    public ForecastViewHolder(View itemView) {
        super(itemView);
        setupView();
        manager = new ForecastItemsManager(itemView.getContext());
    }

    private void setupView(){
        icon = itemView.findViewById(R.id.forecastImageView);
        timeTextView = itemView.findViewById(R.id.timeTextView);
        tempTextView = itemView.findViewById(R.id.tempTextView);
        winTextView = itemView.findViewById(R.id.winTextView);
    }

    public void setDetailView(CityObject items){
        icon.setImageDrawable(manager.getImageWithCode(items.weatherList));
        timeTextView.setText(String.valueOf(items.dt_txt));
        timeTextView.setText(manager.getTimeWithTextDt(String.valueOf(items.dt_txt)));
        tempTextView.setText(manager.getTemp(items.main.temp));
        winTextView.setText(manager.getWin(items.wind.speed));
    }
}
