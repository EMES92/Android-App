package com.example.valerio.myapplication;

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


import java.util.List;

public class MySensorsAdapter extends RecyclerView.Adapter<MySensorsAdapter.MyViewHolder> {

    private List<Integer> sensors;
    private String[] sensorName = new String[]{"sensorTime", "sensorMeteo", "sensorServo", "sensorTemp", "sensorNoise", "sensorLight", "sensorSisma"};
    private ItemClickListener clickListener;


    // constructor
    public MySensorsAdapter(List<Integer> sensors){
        this.sensors = sensors;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // inflate item_layout
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sensor_layout2, null);

        MyViewHolder vh = new MyViewHolder(itemLayoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int i = sensors.get(position);
        holder.sensorName.setText(sensorName[i]);
        holder.sensorButton.setText(sensorName[i]);

        switch (i){
            case 0:
                holder.toggleButton.setVisibility(View.VISIBLE);
                return;
            case 1:
                holder.switch1.setVisibility(View.VISIBLE);
                return;
            case 2:
                holder.toggleButton.setVisibility(View.VISIBLE);
                return;
            case 3:
                holder.switch1.setVisibility(View.VISIBLE);
                return;
            case 4:
                holder.toggleButton.setVisibility(View.VISIBLE);
                return;
            case 5:
                holder.switch1.setVisibility(View.VISIBLE);
                return;
        }

        holder.sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0){
            }

        });
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


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView sensorName;
        public Button sensorButton;
        public ToggleButton toggleButton;
        public SeekBar switch1;

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            sensorName = itemLayoutView.findViewById(R.id.sensor_name);
            sensorButton = itemLayoutView.findViewById(R.id.btn0);

            toggleButton = itemLayoutView.findViewById(R.id.toggle_button);
            switch1 = itemLayoutView.findViewById(R.id.seekBar);

            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
