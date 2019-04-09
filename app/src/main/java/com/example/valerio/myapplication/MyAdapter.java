package com.example.valerio.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<House> addedHouse;
    private ItemClickListener clickListener;
    private Context context;
    private String theme;


    // constructor
    public MyAdapter(List<House> addedHouse, Context context){
        this.addedHouse = addedHouse;
        this.context = context;
    }

    public void removeItem(int position) {
        addedHouse.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, addedHouse.size());
    }
    public House getItem(int position){
        return addedHouse.get(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // inflate item_layout
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, null);

        MyViewHolder vh = new MyViewHolder(itemLayoutView);

        theme = context.getSharedPreferences(MainActivity.getAccountMail()+MainActivity.THEME_PREFERENCES, MODE_PRIVATE).getString(MainActivity.THEME_SAVED, MainActivity.LIGHTTHEME);

        if (theme.equals(MainActivity.DARKTHEME)) {
            itemLayoutView.findViewById(R.id.itemEntry).setBackgroundResource(R.drawable.item_border_dark);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.houseIcon.setImageResource(addedHouse.get(position).getIcon());
        holder.houseName.setText(addedHouse.get(position).getName());
        holder.houseAddress.setText(addedHouse.get(position).getAddress()+", "+addedHouse.get(position).getCity());
        SharedPreferences myPref = context.getSharedPreferences(MainActivity.getAccountMail()+holder.houseName.getText()+MainActivity.STATUSFLAG, MODE_PRIVATE);
        if(!addedHouse.get(position).getSensorTemp()) {
            holder.sensorTempFlag.setImageResource(R.drawable.circle);
            holder.sensorTemp.setText("Sensore di Temperatura non disponibile");
        }
        else if(addedHouse.get(position).getSensorTemp() && myPref.getBoolean("temperatura",  false)){
            holder.sensorTempFlag.setImageResource(R.drawable.green_circle);
        }
        if(!addedHouse.get(position).getSensorNoise()) {
            holder.sensorNoiseFlag.setImageResource(R.drawable.circle);
            holder.sensorNoise.setText("Sensore di Rumore non disponibile");
        }
        else if(addedHouse.get(position).getSensorNoise() && myPref.getBoolean("noise",  false)){
            holder.sensorNoiseFlag.setImageResource(R.drawable.green_circle);
        }
        if(!addedHouse.get(position).getSensorLight()) {
            holder.sensorLightFlag.setImageResource(R.drawable.circle);
            holder.sensorLight.setText("Sensore di Luminosità non disponibile");
        }
        else if(addedHouse.get(position).getSensorLight() && myPref.getBoolean("light",  false)){
            holder.sensorLightFlag.setImageResource(R.drawable.green_circle);
        }
        if(!addedHouse.get(position).getSensorSisma()){
            holder.sensorSismaFlag.setImageResource(R.drawable.circle);
            holder.sensorSisma.setText("Sensore di Vibrazione non disponibile");
        }
        else if(addedHouse.get(position).getSensorSisma() && myPref.getBoolean("sisma",  false)){
            holder.sensorSismaFlag.setImageResource(R.drawable.green_circle);
        }


        if (theme.equals(MainActivity.DARKTHEME)) {
            holder.houseAddress.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        if(addedHouse != null)
            return addedHouse.size();
        else
            return 0;
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView houseName;
        public TextView houseAddress;
        public ImageView houseIcon;

        public ImageView sensorTempFlag;
        public ImageView sensorNoiseFlag;
        public ImageView sensorLightFlag;
        public ImageView sensorSismaFlag;
        public TextView sensorTemp;
        public TextView sensorNoise;
        public TextView sensorLight;
        public TextView sensorSisma;

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            houseName = itemLayoutView.findViewById(R.id.item_title);
            houseAddress = itemLayoutView.findViewById(R.id.item_address);
            houseIcon = itemLayoutView.findViewById(R.id.item_icon);

            sensorTemp = itemLayoutView.findViewById(R.id.textTemp);
            sensorNoise = itemLayoutView.findViewById(R.id.textNoise);
            sensorLight = itemLayoutView.findViewById(R.id.textLight);
            sensorSisma = itemLayoutView.findViewById(R.id.textSisma);

            sensorTempFlag = itemLayoutView.findViewById(R.id.flagTemp);
            sensorNoiseFlag = itemLayoutView.findViewById(R.id.flagNoise);
            sensorLightFlag = itemLayoutView.findViewById(R.id.flagLight);
            sensorSismaFlag = itemLayoutView.findViewById(R.id.flagSisma);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
