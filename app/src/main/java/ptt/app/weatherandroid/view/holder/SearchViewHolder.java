package ptt.app.weatherandroid.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ptt.app.weatherandroid.R;

public class SearchViewHolder extends RecyclerView.ViewHolder{

    private TextView cityNameTextView;
    public RelativeLayout contentView;

    public SearchViewHolder(View itemView) {
        super(itemView);
        setupView();
    }

    private void setupView(){
        contentView = itemView.findViewById(R.id.contentView);
        cityNameTextView = itemView.findViewById(R.id.cityNameTextView);
    }

    public void setCityName(String text){
        cityNameTextView.setText(text);
    }
}
