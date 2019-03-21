package com.example.valerio.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        String theme = context.getSharedPreferences(MainActivity.getAccountMail()+HomeFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(HomeFragment.THEME_SAVED, HomeFragment.LIGHTTHEME);
        Log.w("colore","theme my adapter "+theme);

        if (theme.equals(HomeFragment.DARKTHEME)) {

            itemLayoutView.findViewById(R.id.itemEntry).setBackgroundColor(0xFF757575);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.houseIcon.setImageResource(addedHouse.get(position).getIcon());
        holder.houseName.setText(addedHouse.get(position).getName());
        holder.houseAddress.setText(addedHouse.get(position).getAddress());

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

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            houseName = itemLayoutView.findViewById(R.id.item_title);
            houseAddress = itemLayoutView.findViewById(R.id.item_address);
            houseIcon = itemLayoutView.findViewById(R.id.item_icon);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
