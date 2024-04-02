package com.minhui.networkcapture.RadarActivities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class MobsActivity extends AppCompatActivity
{
    CheckBox tier1;
    CheckBox tier2;
    CheckBox tier3;
    CheckBox tier4;
    CheckBox tier5;
    CheckBox tier6;
    CheckBox tier7;
    CheckBox tier8;

    CheckBox enchant0;
    CheckBox enchant1;
    CheckBox enchant2;
    CheckBox enchant3;
    CheckBox enchant4;

    CheckBox harvestable;
    CheckBox skinnable;
    CheckBox other;
    CheckBox showHp;
    CheckBox boss;

    TextView textView;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mobs);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar));
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        textView = findViewById(R.id.toolbarTitle);
        textView.setText("Mobs Settings");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        tier1 = findViewById(R.id.tier1);
        tier2 = findViewById(R.id.tier2);
        tier3 = findViewById(R.id.tier3);
        tier4 = findViewById(R.id.tier4);
        tier5 = findViewById(R.id.tier5);
        tier6 = findViewById(R.id.tier6);
        tier7 = findViewById(R.id.tier7);
        tier8 = findViewById(R.id.tier8);
        showHp = findViewById(R.id.mobHp);

        enchant0 = findViewById(R.id.enchant0);
        enchant1 = findViewById(R.id.enchant1);
        enchant2 = findViewById(R.id.enchant2);
        enchant3 = findViewById(R.id.enchant3);
        enchant4 = findViewById(R.id.enchant4);

        harvestable = findViewById(R.id.mobHarvestable);
        skinnable = findViewById(R.id.mobSkinnable);
        other = findViewById(R.id.mobOther);
        boss = findViewById(R.id.mobBoss);

        tier1.setChecked(RadarSettings.getInstance().mobTiers[0]);
        tier2.setChecked(RadarSettings.getInstance().mobTiers[1]);
        tier3.setChecked(RadarSettings.getInstance().mobTiers[2]);
        tier4.setChecked(RadarSettings.getInstance().mobTiers[3]);
        tier5.setChecked(RadarSettings.getInstance().mobTiers[4]);
        tier6.setChecked(RadarSettings.getInstance().mobTiers[5]);
        tier7.setChecked(RadarSettings.getInstance().mobTiers[6]);
        tier8.setChecked(RadarSettings.getInstance().mobTiers[7]);

        enchant0.setChecked(RadarSettings.getInstance().mobEnchants[0]);
        enchant1.setChecked(RadarSettings.getInstance().mobEnchants[1]);
        enchant2.setChecked(RadarSettings.getInstance().mobEnchants[2]);
        enchant3.setChecked(RadarSettings.getInstance().mobEnchants[3]);
        enchant4.setChecked(RadarSettings.getInstance().mobEnchants[4]);
        showHp.setChecked(RadarSettings.getInstance().mobHp);

        harvestable.setChecked(RadarSettings.getInstance().mobHarvestable);
        skinnable.setChecked(RadarSettings.getInstance().mobSkinnable);
        other.setChecked(RadarSettings.getInstance().mobOther);
        boss.setChecked(RadarSettings.getInstance().mobBoss);

        tier1.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobTiers[0] = b;

            editor.putBoolean("mobTier1", b);
            editor.apply();

            Log.d("mobTier1", ""+sharedPreferences.getBoolean("mobTier1",false));
        });


        tier2.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobTiers[1] = b;

            editor.putBoolean("mobTier2", b);
            editor.apply();

        });

        tier3.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobTiers[2] = b;

            editor.putBoolean("mobTier3", b);
            editor.apply();
        });

        tier4.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobTiers[3] = b;

            editor.putBoolean("mobTier4", b);
            editor.apply();
        });

        tier5.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobTiers[4] = b;

            editor.putBoolean("mobTier5", b);
            editor.apply();
        });

        tier6.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobTiers[5] = b;

            editor.putBoolean("mobTier6", b);
            editor.apply();
        });

        tier7.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobTiers[6] = b;

            editor.putBoolean("mobTier7", b);
            editor.apply();
        });

        tier8.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobTiers[7] = b;

            editor.putBoolean("mobTier8", b);
            editor.apply();
        });

        enchant0.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobEnchants[0] = b;

            editor.putBoolean("mobEnchant0", b);
            editor.apply();
        });

        enchant1.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobEnchants[1] = b;

            editor.putBoolean("mobEnchant1", b);
            editor.apply();
        });

        enchant2.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobEnchants[2] = b;

            editor.putBoolean("mobEnchant2", b);
            editor.apply();
        });

        enchant3.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobEnchants[2] = b;

            editor.putBoolean("mobEnchant3", b);
            editor.apply();
        });

        enchant4.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobEnchants[4] = b;

            editor.putBoolean("mobEnchant4", b);
            editor.apply();
        });

        showHp.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobHp = b;

            editor.putBoolean("mobHp", b);
            editor.apply();
        });

        harvestable.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobHarvestable = b;

            editor.putBoolean("mobHarvestable", b);
            editor.apply();
        });


        skinnable.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobSkinnable = b;

            editor.putBoolean("mobSkinnable", b);
            editor.apply();
        });


        other.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobOther = b;

            editor.putBoolean("mobOther", b);
            editor.apply();
        });

        boss.setOnCheckedChangeListener((compoundButton, b) ->
        {
            RadarSettings.getInstance().mobBoss = b;

            editor.putBoolean("mobBoss", b);
            editor.apply();
        });
    }
}
