package com.example.valerio.myapplication;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


public class MyPreferenceActivity extends PreferenceActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.tool_bar, root, false);
        bar.setTitle("Settings");
        root.addView(bar, 0); // insert at top

        getFragmentManager().beginTransaction().replace(android.R.id.content, new Fragment_Settings()).commit();

    }
}
