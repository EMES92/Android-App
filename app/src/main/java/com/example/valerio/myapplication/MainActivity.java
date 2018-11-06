package com.example.valerio.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import org.telegram.passport.PassportScope;
import org.telegram.passport.PassportScopeElementOne;
import org.telegram.passport.PassportScopeElementOneOfSeveral;
import org.telegram.passport.TelegramLoginButton;
import org.telegram.passport.TelegramPassport;

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
    private TelegramLoginButton loginButton;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.btn1);

        button.setOnClickListener(new OnClickListener() {

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
    }


}
