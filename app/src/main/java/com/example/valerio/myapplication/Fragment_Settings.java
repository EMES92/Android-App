package com.example.valerio.myapplication;

import android.os.Bundle;
import android.preference.PreferenceFragment;


public class Fragment_Settings extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}