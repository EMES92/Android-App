package com.example.valerio.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MySensorsAdapter extends RecyclerView.Adapter<MySensorsAdapter.MyViewHolder> {

    private List<Integer> sensors;
    private String[] sensorName = new String[]{"sensorServo", "sensorTemp", "sensorNoise", "sensorLight", "sensorSisma"};
    private ItemClickListener clickListener;
    private Context context;
    BitmapDrawable a;


    // constructor
    public MySensorsAdapter(List<Integer> sensors, Context context){
        this.sensors = sensors;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // inflate item_layout
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sensor_layout2, null);

        MyViewHolder vh = new MyViewHolder(itemLayoutView);
        String theme = context.getSharedPreferences(MainActivity.getAccountMail()+HomeFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(HomeFragment.THEME_SAVED, HomeFragment.LIGHTTHEME);
        if (theme.equals(HomeFragment.DARKTHEME)) {
            itemLayoutView.setBackgroundColor(0xFF000F00);
            itemLayoutView.findViewById(R.id.itemSensorEntry).setBackgroundColor(0xFF757575);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int i = sensors.get(position);
        holder.sensorName.setText(sensorName[i]);
        holder.sensorButton.setText(sensorName[i]);

        switch (i){
            case 0:
                holder.seek1.setVisibility(View.VISIBLE);
                return;
            case 1:
                holder.toggleButton.setVisibility(View.VISIBLE);
                return;
            case 2:
                holder.toggleButton.setVisibility(View.VISIBLE);
                return;
            case 3:
                holder.toggleButton.setVisibility(View.VISIBLE);
                return;
            case 4:
                holder.toggleButton.setVisibility(View.VISIBLE);
                return;
        }

//        holder.sensorButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0){
//                Log.w("button","arg0  "+arg0);
//            }
//
//        });
    }

    @Override
    public int getItemCount() {
        if(sensors != null)
            return sensors.size();
        else
            return 0;
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void getBitmapFromURL(String imageUrl) {

        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.w("bitmap","bitmap");
                a = new BitmapDrawable(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        return;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView sensorName;
        public Button sensorButton;
        public ToggleButton toggleButton;
        public SeekBar seek1;

        //Drawable dr = new BitmapDrawable(myImage);

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            getBitmapFromURL("https://app.ubidots.com/ubi/getchart/sWbrGVl1vNM_9EG25In2kJbIgMk");

            itemLayoutView.findViewById(R.id.itemSensorEntry).setBackgroundDrawable(a);
            sensorName = itemLayoutView.findViewById(R.id.sensor_name);
            sensorButton = itemLayoutView.findViewById(R.id.btn0);

            toggleButton = itemLayoutView.findViewById(R.id.toggle_button);
            seek1 = itemLayoutView.findViewById(R.id.seekBar);

            itemLayoutView.setOnClickListener(this);

            itemLayoutView.setOnClickListener(this);
            sensorButton.setOnClickListener(this);
            toggleButton.setOnClickListener(this);
            seek1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                Log.w("button","adapter  " +view.getId());
                clickListener.onClick(view, getAdapterPosition());
            }
        }
    }
}
