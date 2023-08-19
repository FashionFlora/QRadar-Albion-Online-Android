package com.minhui.networkcapture.RadarActivities;

import static android.widget.CompoundButton.*;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.minhui.networkcapture.R;
import com.minhui.networkcapture.RadarView.RadarSettings;

public class SettingsActivity extends AppCompatActivity {


    CheckBox chestGreen;
    CheckBox chestBlue;
    CheckBox chestPurple;
    CheckBox chestLegendary;


    Toolbar toolbar;
    TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar));
        setContentView(R.layout.activity_settings);





    }
}
