package com.minhui.networkcapture.RadarActivities;

import static android.widget.CompoundButton.*;

import android.annotation.SuppressLint;
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

public class ChestsActivity extends AppCompatActivity
{
    CheckBox chestGreen;
    CheckBox chestBlue;
    CheckBox chestPurple;
    CheckBox chestLegendary;
    Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar));
        setContentView(R.layout.activity_chests);

        textView = findViewById(R.id.toolbarTitle);
        textView.setText("Chests Settings");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        actionBar.setTitle("");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        chestGreen = findViewById(R.id.chestGreen);
        chestBlue = findViewById(R.id.chestBlue);
        chestPurple = findViewById(R.id.chestPurple);
        chestLegendary = findViewById(R.id.chestLegendary);

        chestGreen.setChecked(RadarSettings.getInstance().chests.get("standard"));
        chestBlue.setChecked(RadarSettings.getInstance().chests.get("uncommon"));
        chestPurple.setChecked(RadarSettings.getInstance().chests.get("rare"));
        chestLegendary.setChecked(RadarSettings.getInstance().chests.get("legendary"));

        chestGreen.setOnCheckedChangeListener((compoundButton, b) ->
        {
            editor.putBoolean("chestStandard",b);
            editor.apply();
            RadarSettings.getInstance().chests.remove("standard");
            RadarSettings.getInstance().chests.put("standard",b);
        });

        chestBlue.setOnCheckedChangeListener((compoundButton, b) ->
        {
            editor.putBoolean("chestUncommon",b);
            editor.apply();
            RadarSettings.getInstance().chests.remove("uncommon");
            RadarSettings.getInstance().chests.put("uncommon",b);
        });

        chestPurple.setOnCheckedChangeListener((compoundButton, b) ->
        {
            editor.putBoolean("chestRare",b);
            editor.apply();
            RadarSettings.getInstance().chests.remove("rare");
            RadarSettings.getInstance().chests.put("rare",b);
        });

        chestLegendary.setOnCheckedChangeListener((compoundButton, b) ->
        {
            editor.putBoolean("chestLegendary",b);
            editor.apply();
            RadarSettings.getInstance().chests.remove("legendary");
            RadarSettings.getInstance().chests.put("legendary",b);
        });
    }
}
