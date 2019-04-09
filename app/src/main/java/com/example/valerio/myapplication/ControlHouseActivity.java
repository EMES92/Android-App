package com.example.valerio.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

import static com.example.valerio.myapplication.MainActivity.getAccountMail;

public class ControlHouseActivity extends AppCompatActivity implements ItemClickListener{

    private Toolbar toolbar;
    private String name;
    private String address;
    private String city;

    private boolean sensorServo;
    private boolean sensorTemp;
    private boolean sensorNoise;
    private boolean sensorLight;
    private boolean sensorSisma;

    private boolean[] sensors;

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

        String theme = getSharedPreferences(getAccountMail()+MainActivity.THEME_PREFERENCES, MODE_PRIVATE).getString(MainActivity.THEME_SAVED, MainActivity.LIGHTTHEME);

        if (theme.equals(MainActivity.LIGHTTHEME)) {
            setTheme(R.style.CustomStyle_LightTheme);
        } else {
            setTheme(R.style.CustomStyle_DarkTheme);
        }

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
        sensorServo = getIntent().getBooleanExtra("sensorServo", false);
        sensorTemp = getIntent().getBooleanExtra("sensorTemp", false);
        sensorNoise = getIntent().getBooleanExtra("sensorNoise", false);
        sensorLight = getIntent().getBooleanExtra("sensorLight", false);
        sensorSisma = getIntent().getBooleanExtra("sensorSisma", false);

        sensors = new boolean[]{sensorServo, sensorTemp, sensorNoise, sensorLight, sensorSisma};


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ControlHouseActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // adapter
        adapter = new MySensorsAdapter(sensorsL, getApplicationContext(), name);
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
        int i = sensorsL.get(position);
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
        String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
        String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
        String text;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        switch (i){
            case 0:
                if(v.getId() == R.id.btn0) {
                    final Dialog m = new Dialog(ControlHouseActivity.this);
                    m.setContentView(R.layout.graphic_dialog);
                    DisplayMetrics metrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);

                    int width = (size.x / (int) metrics.density) * 5 / 10;
                    int height = (size.y / (int) metrics.density) * 2 / 10;
                    String widthS = String.valueOf(width);
                    String heightS = String.valueOf(height);

                    String html = "<iframe width=" + widthS + " height=" + heightS +
                            " style=\"border: 1px solid #cccccc;\" " +
                            "src=\"https://app.ubidots.com/ubi/getchart/9HonmohlhGD6IFhNya4DFATb3As\"></iframe>";
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
                }
            case 1:
                if(v.getId() == R.id.btn0) {
                    final Dialog m = new Dialog(ControlHouseActivity.this);
                    m.setContentView(R.layout.graphic_dialog);
                    DisplayMetrics metrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);

                    int width = (size.x / (int) metrics.density) * 5 / 10;
                    int height = (size.y / (int) metrics.density) * 5 / 10;
                    String widthS = String.valueOf(width);
                    String heightS = String.valueOf(height);

                    String html = "<iframe width=" + widthS + " height=" + heightS +
                            " style=\"border: 1px solid #cccccc;\" " +
                            "src=\"https://app.ubidots.com/ubi/getchart/s9kcuoZcof_al7tpvsAiiPCfB4Q\"></iframe>";
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
                }
                else if(v.getId() == R.id.toggle_button){
                    ToggleButton current = v.findViewById(v.getId());
                    SharedPreferences statusPreferences = getSharedPreferences(getAccountMail()+name+MainActivity.STATUSFLAG, Context.MODE_PRIVATE);
                    SharedPreferences.Editor statusEditor = statusPreferences.edit();
                    statusEditor.putBoolean("temperatura", current.isChecked());
                    statusEditor.apply();

                    text = "temperatura on/off";
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
                }
                return;
            case 2:
                if(v.getId() == R.id.btn0) {
                    final Dialog m = new Dialog(ControlHouseActivity.this);
                    m.setContentView(R.layout.graphic_dialog);
                    DisplayMetrics metrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);

                    int width = (size.x / (int) metrics.density) * 5 / 10;
                    int height = (size.y / (int) metrics.density) * 5 / 10;
                    String widthS = String.valueOf(width);
                    String heightS = String.valueOf(height);

                    String html = "<iframe width=" + widthS + " height=" + heightS +
                            " style=\"border: 1px solid #cccccc;\" " +
                            "src=\"https://app.ubidots.com/ubi/getchart/page/jqgpFVN0gQwfr28Pyp2ZeDLb37Q\"></iframe>";
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
                }
                else if(v.getId() == R.id.toggle_button){
                    ToggleButton current = v.findViewById(v.getId());
                    SharedPreferences statusPreferences = getSharedPreferences(getAccountMail()+name+MainActivity.STATUSFLAG, Context.MODE_PRIVATE);
                    SharedPreferences.Editor statusEditor = statusPreferences.edit();
                    statusEditor.putBoolean("noise", current.isChecked());
                    statusEditor.apply();
                    text = "noise on/off";
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
                }
                return;
            case 3:
                if(v.getId() == R.id.btn0) {
                    final Dialog m = new Dialog(ControlHouseActivity.this);
                    m.setContentView(R.layout.graphic_dialog);
                    DisplayMetrics metrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);

                    int width = (size.x / (int) metrics.density) * 5 / 10;
                    int height = (size.y / (int) metrics.density) * 5 / 10;
                    String widthS = String.valueOf(width);
                    String heightS = String.valueOf(height);

                    String html = "<iframe width=" + widthS + " height=" + heightS +
                            " style=\"border: 1px solid #cccccc;\" " +
                            "src=\"https://app.ubidots.com/ubi/getchart/page/5KjO4tnZ1vviqhHCjrDI8Mql7pE\"></iframe>";
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
                }
                else if(v.getId() == R.id.toggle_button){
                    ToggleButton current = v.findViewById(v.getId());
                    SharedPreferences statusPreferences = getSharedPreferences(getAccountMail()+name+MainActivity.STATUSFLAG, Context.MODE_PRIVATE);
                    SharedPreferences.Editor statusEditor = statusPreferences.edit();
                    statusEditor.putBoolean("light", current.isChecked());
                    statusEditor.apply();
                    text = "light on/off";
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
                }
                return;
            case 4:
                if(v.getId() == R.id.btn0) {
                    final Dialog m = new Dialog(ControlHouseActivity.this);
                    m.setContentView(R.layout.graphic_dialog);
                    DisplayMetrics metrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);

                    int width = (size.x / (int) metrics.density) * 5 / 10;
                    int height = (size.y / (int) metrics.density) * 5 / 10;
                    String widthS = String.valueOf(width);
                    String heightS = String.valueOf(height);

                    String html = "<iframe width=" + widthS + " height=" + heightS +
                            " style=\"border: 1px solid #cccccc;\" " +
                            "src=\"https://app.ubidots.com/ubi/getchart/page/RvDLt4qvgmQ3dsMI6AE0JZbZ-uQ\"></iframe>";
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
                }
                else if(v.getId() == R.id.toggle_button){
                    ToggleButton current = v.findViewById(v.getId());
                    SharedPreferences statusPreferences = getSharedPreferences(getAccountMail()+name+MainActivity.STATUSFLAG, Context.MODE_PRIVATE);
                    SharedPreferences.Editor statusEditor = statusPreferences.edit();
                    statusEditor.putBoolean("sisma", current.isChecked());
                    statusEditor.apply();
                    text = "sisma on/off";
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
                }
                return;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getSharedPreferences(getAccountMail()+MainActivity.THEME_PREFERENCES, MODE_PRIVATE).getBoolean(MainActivity.RECREATE_CONTROL_ACTIVITY, false)) {
            SharedPreferences.Editor editor = getSharedPreferences(getAccountMail()+MainActivity.THEME_PREFERENCES, MODE_PRIVATE).edit();
            editor.putBoolean(MainActivity.RECREATE_CONTROL_ACTIVITY, false);
            editor.apply();
            recreate();
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
        getMenuInflater().inflate(R.menu.nav_drawer_onlysett, menu);
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
            Intent settings = new Intent(ControlHouseActivity.this, MyPreferenceActivity.class);
            startActivity(settings);
            return true;
        }
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
