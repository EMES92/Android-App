package com.example.valerio.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


public class MyPreferenceActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String theme = getSharedPreferences(MainActivity.getAccountMail()+HomeFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(HomeFragment.THEME_SAVED, HomeFragment.LIGHTTHEME);
        Log.w("colore","theme preference activitu "+theme);

        if (theme.equals(HomeFragment.LIGHTTHEME)) {
            setTheme(R.style.CustomStyle_LightTheme);
        } else {
            setTheme(R.style.CustomStyle_DarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypreference);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getFragmentManager().beginTransaction().replace(R.id.mycontent, new Fragment_Settings()).commit();

    }
}
