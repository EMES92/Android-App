package com.example.valerio.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.content.Context.MODE_PRIVATE;

public class MySensorsAdapter extends RecyclerView.Adapter<MySensorsAdapter.MyViewHolder> {

    private List<Integer> sensors;
    private String nameHouse;
    private String[] sensorName = new String[]{"sensorServo", "sensorTemp", "sensorNoise", "sensorLight", "sensorSisma"};
    private ItemClickListener clickListener;
    private Context context;
    BitmapDrawable a;
    private StoreRetrieveData storeRetrieveData;

    // constructor
    public MySensorsAdapter(List<Integer> sensors, Context context, String nameHouse){
        this.nameHouse=nameHouse;
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
        String theme = context.getSharedPreferences(MainActivity.getAccountMail()+MainActivity.THEME_PREFERENCES, MODE_PRIVATE).getString(MainActivity.THEME_SAVED, MainActivity.LIGHTTHEME);
        if (theme.equals(MainActivity.DARKTHEME)) {
            itemLayoutView.findViewById(R.id.itemSensorEntry).setBackgroundResource(R.drawable.item_border_dark);
            //itemLayoutView.findViewById(R.id.itemSensorEntry).setBackgroundColor(0xFF757575);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int i = sensors.get(position);
        holder.sensorName.setText(sensorName[i]);
        holder.sensorButton.setText(sensorName[i]);
        final SharedPreferences myPref = context.getSharedPreferences(MainActivity.getAccountMail()+nameHouse+MainActivity.STATUSFLAG, MODE_PRIVATE);
        Boolean b;
        switch (i){
            case 0:
                holder.seek1.setVisibility(View.VISIBLE);
                holder.seek1.setProgress(myPref.getInt("servo", 0));
                holder.seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    int index = 0;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        index = i;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {}

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        myPref.edit().putInt("servo", index).apply();
                        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                        String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                        String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                        String text = "servo "+index;
                        urlString = String.format(urlString, apiToken, chatId, text);
                        AsyncHttpClient client = new AsyncHttpClient();
                        RequestParams params = new RequestParams();
                        params.put("key", "value");
                        params.put("more", "data");
                        client.get(urlString, params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {}
                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {}
                        });
                    }
                });

                return;
            case 1:
                holder.toggleButton.setVisibility(View.VISIBLE);
                b = myPref.getBoolean("temperatura",  false);
                holder.toggleButton.setChecked(b);
                return;
            case 2:
                holder.toggleButton.setVisibility(View.VISIBLE);
                b = myPref.getBoolean("noise",  false);
                holder.toggleButton.setChecked(b);
                return;
            case 3:
                holder.toggleButton.setVisibility(View.VISIBLE);
                b = myPref.getBoolean("light",  false);
                holder.toggleButton.setChecked(b);
                return;
            case 4:
                holder.toggleButton.setVisibility(View.VISIBLE);
                b = myPref.getBoolean("sisma",  false);
                holder.toggleButton.setChecked(b);
                return;
        }
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

//    public void getBitmapFromURL(String imageUrl) {
//
//        Picasso.get().load(imageUrl).into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                Log.w("bitmap","bitmap");
//                a = new BitmapDrawable(bitmap);
//            }
//
//            @Override
//            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        });
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView sensorName;
        public Button sensorButton;
        public ToggleButton toggleButton;
        public SeekBar seek1;

        //Drawable dr = new BitmapDrawable(myImage);

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            //getBitmapFromURL("https://app.ubidots.com/ubi/getchart/sWbrGVl1vNM_9EG25In2kJbIgMk");
            //itemLayoutView.findViewById(R.id.itemSensorEntry).setBackgroundDrawable(a);

            sensorName = itemLayoutView.findViewById(R.id.sensor_name);
            sensorButton = itemLayoutView.findViewById(R.id.btn0);

            toggleButton = itemLayoutView.findViewById(R.id.toggle_button);
            seek1 = itemLayoutView.findViewById(R.id.seekBar);


            //itemLayoutView.setOnClickListener(this);
            sensorButton.setOnClickListener(this);
            toggleButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClick(view, getAdapterPosition());
            }
        }
    }
}
