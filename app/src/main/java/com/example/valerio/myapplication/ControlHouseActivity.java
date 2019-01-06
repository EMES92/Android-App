package com.example.valerio.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class ControlHouseActivity extends AppCompatActivity implements ItemClickListener{

    private Toolbar toolbar;
    private String name;
    private String address;
    private String city;


    private boolean sensorTime;
    private boolean sensorMeteo ;
    private boolean sensorServo;
    private boolean sensorTemp;
    private boolean sensorNoise;
    private boolean sensorLight;
    private boolean sensorSisma;
    //private boolean sensor8;

    private boolean[] sensors;

    private Button buttonTime;
    private Button buttonMeteo;
    private Button buttonServo;
    private Button buttonTemp;
    private Button buttonNoise;
    private Button buttonLight;
    private Button buttonSisma;
    //private Button button8;


    Typeface weatherFont;

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;

    private RecyclerView recyclerView;
    private MySensorsAdapter adapter;

    private ArrayList<Integer> sensorsL = new ArrayList<>();


    Handler handler;

    public ControlHouseActivity(){
        handler = new Handler();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_house);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cityField = (TextView) findViewById(R.id.city_field);
        updatedField = (TextView) findViewById(R.id.updated_field);
        detailsField = (TextView) findViewById(R.id.details_field);
        currentTemperatureField = (TextView) findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView) findViewById(R.id.weather_icon);
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weather.ttf");
        weatherIcon.setTypeface(weatherFont);


        name = getIntent().getStringExtra("name");
        address = getIntent().getStringExtra("address");
        city = getIntent().getStringExtra("city");
        sensorTime = getIntent().getBooleanExtra("sensorTime", false);
        sensorMeteo = getIntent().getBooleanExtra("sensorMeteo", false);
        sensorServo = getIntent().getBooleanExtra("sensorServo", false);
        sensorTemp = getIntent().getBooleanExtra("sensorTemp", false);
        sensorNoise = getIntent().getBooleanExtra("sensorNoise", false);
        sensorLight = getIntent().getBooleanExtra("sensorLight", false);
        sensorSisma = getIntent().getBooleanExtra("sensorSisma", false);

        sensors = new boolean[]{sensorTime, sensorMeteo, sensorServo, sensorTemp, sensorNoise, sensorLight, sensorSisma};


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ControlHouseActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // adapter
        adapter = new MySensorsAdapter(sensorsL);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        for(int i = 0; i<sensors.length;i++) {
            if (sensors[i] == true) {
                addItemList(i);
            }
        }

        updateWeatherData(city);

    }

    @Override
    public void onClick(View v, int position){
        //boolean sensor = sensorsMap.get(position);
        int i = sensorsL.get(position);
        String urlString;
        String apiToken;
        String chatId;
        String text;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        switch (i){
            case 0:
                urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                text = "time";
                urlString = String.format(urlString, apiToken, chatId, text);
                client = new AsyncHttpClient();
                params = new RequestParams();
                params.put("key", "value");
                params.put("more", "data");
                client.get(urlString, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                return;
            case 1:
                urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                text = "meteo";
                urlString = String.format(urlString, apiToken, chatId, text);
                client = new AsyncHttpClient();
                params = new RequestParams();
                params.put("key", "value");
                params.put("more", "data");
                client.get(urlString, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                return;
            case 2:
                urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                text = "servo";
                urlString = String.format(urlString, apiToken, chatId, text);
                client = new AsyncHttpClient();
                params = new RequestParams();
                params.put("key", "value");
                params.put("more", "data");
                client.get(urlString, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                return;
            case 3:
                urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                text = "temperatura";
                urlString = String.format(urlString, apiToken, chatId, text);
                client = new AsyncHttpClient();
                params = new RequestParams();
                params.put("key", "value");
                params.put("more", "data");
                client.get(urlString, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                return;
            case 4:
                //                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
//                String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
//                String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
//                String text = "noise";
//                urlString = String.format(urlString, apiToken, chatId, text);
//                AsyncHttpClient client = new AsyncHttpClient();
//                RequestParams params = new RequestParams();
//                params.put("key", "value");
//                params.put("more", "data");
//                client.get(urlString, params, new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                    }
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//                    }
//                });


                final Dialog m = new Dialog(ControlHouseActivity.this);
                m.setContentView(R.layout.graphic_dialog);

                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int heightPix = metrics.heightPixels;
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                int width = (size.x / (int) metrics.density)*5/10;
                int height = (size.y / (int) metrics.density)*5/10;
                String widthS = String.valueOf(width);
                String heightS = String.valueOf(height);

                String html = "<iframe width="+widthS+" height="+heightS+" style=\"border: 1px solid #cccccc;\" src=\"https://app.ubidots.com/ubi/getchart/sWbrGVl1vNM_9EG25In2kJbIgMk\"></iframe>";
                WebView webview = (WebView) m.findViewById(R.id.webV);
                webview.getSettings().setJavaScriptEnabled(true);
                webview.loadData(html, "text/html", null);
                m.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        m.dismiss();
                    }
                });

                m.show();
                return;
            case 5:
                urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                text = "light";
                urlString = String.format(urlString, apiToken, chatId, text);
                client = new AsyncHttpClient();
                params = new RequestParams();
                params.put("key", "value");
                params.put("more", "data");
                client.get(urlString, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                return;
            case 6:
                urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                text = "sisma";
                urlString = String.format(urlString, apiToken, chatId, text);
                client = new AsyncHttpClient();
                params = new RequestParams();
                params.put("key", "value");
                params.put("more", "data");
                client.get(urlString, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                return;
        }


    }

    public void addItemList(int i){
        sensorsL.add(i);
        recyclerView.setAdapter(adapter);
    }


    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            getFragmentManager().beginTransaction()
//                    .replace(android.R.id.content, new Fragment_Settings()).addToBackStack(null)
//                    .commit();
//            return true;
            //
//            Fragment_Settings settingsfragment = new Fragment_Settings();
//            android.support.v4.app.FragmentTransaction settingsFragmentTransaction
//                    = getSupportFragmentManager().beginTransaction();
//            settingsFragmentTransaction.replace(R.id.frame,settingsfragment);
//            settingsFragmentTransaction.commit();
//            return true;
        }
//        else if (id == R.id.action_logOut) {
//            if (acct == null) {
//                Intent turnLoginPage = new Intent(MainActivity.this, Login.class);
//                startActivity(turnLoginPage);
//                LoginManager.getInstance().logOut();
//            } else {
//                Intent turnLoginPage = new Intent(MainActivity.this, Login.class);
//                turnLoginPage.putExtra("logout", 1);
//                startActivity(turnLoginPage);
//            }
//            finish();
//        }
        return super.onOptionsItemSelected(item);
    }


    //Meteo
    private void updateWeatherData(final String city){
        new Thread(){
            public void run(){
                final JSONObject json = MeteoService.getJSON(ControlHouseActivity.this, city);
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(ControlHouseActivity.this,
                                    getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json){
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            detailsField.setText(
                    details.getString("description").toUpperCase(Locale.US) +
                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");

            currentTemperatureField.setText(
                    String.format("%.2f", main.getDouble("temp"))+ " ℃");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            updatedField.setText("Last update: " + updatedOn);

            setWeatherIcon(details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000);

        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch(id) {
                case 2 : icon = getString(R.string.weather_thunder);
                    break;
                case 3 : icon = getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = getString(R.string.weather_foggy);
                    break;
                case 8 : icon = getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = getString(R.string.weather_snowy);
                    break;
                case 5 : icon = getString(R.string.weather_rainy);
                    break;
            }
        }
        weatherIcon.setText(icon);
    }
}
