package com.example.valerio.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);
        button5 = (Button) findViewById(R.id.btn5);
        button6 = (Button) findViewById(R.id.btn6);
        button7 = (Button) findViewById(R.id.btn7);
        button8 = (Button) findViewById(R.id.btn8);

        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                String text = "time";
                urlString = String.format(urlString, apiToken, chatId, text);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
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

        });

        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                String text = "meteo";
                urlString = String.format(urlString, apiToken, chatId, text);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
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

        });
        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                String text = "servo";
                urlString = String.format(urlString, apiToken, chatId, text);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
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

        });
        button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                String text = "temperatura";
                urlString = String.format(urlString, apiToken, chatId, text);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
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

        });
        button5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                String text = "noise";
                urlString = String.format(urlString, apiToken, chatId, text);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
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

        });
        button6.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                String text = "light";
                urlString = String.format(urlString, apiToken, chatId, text);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
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

        });
        button7.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                String text = "sisma";
                urlString = String.format(urlString, apiToken, chatId, text);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
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

        });
        button8.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
                String apiToken = "603981379:AAFux3TNvauoPfIN0a1BWAnQ60IRcWg3Oos"; //'"my_bot_api_token";EmesAndroid Bot
                String chatId = "-1001476670474";//"@my_channel_name";  // id private = -1001476670474 del canale domestico
                String text = "meteo";
                urlString = String.format(urlString, apiToken, chatId, text);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
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

        });
    }


}
