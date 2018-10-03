package ptt.app.weatherandroid.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ptt.app.weatherandroid.R;
import ptt.app.weatherandroid.models.entity.WeatherDetailObject;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private TextView title, value;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        setupView();
    }

    private void setupView(){
        title = itemView.findViewById(R.id.TitleNameTextView);
        value = itemView.findViewById(R.id.valueNameTextView);
    }

    public void setViewDetail(WeatherDetailObject data){
        title.setText(data.getTitle());
        value.setText(data.getValue());
    }
}
