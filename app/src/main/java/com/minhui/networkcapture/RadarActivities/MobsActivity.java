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

public class MobsActivity extends AppCompatActivity {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



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
        tier1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobTier1", b);
                editor.apply();


                RadarSettings.getInstance().mobTiers[0] = b;


                Log.d("mobTier1", ""+sharedPreferences.getBoolean("mobTier1",false));




            }
        });


        tier2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobTier2", b);


                RadarSettings.getInstance().mobTiers[1] = b;
                editor.apply();

            }
        });
        tier3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobTier3", b);


                RadarSettings.getInstance().mobTiers[2] = b;
                editor.apply();


            }
        });
        tier4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobTier4", b);


                RadarSettings.getInstance().mobTiers[3] = b;
                editor.apply();

            }
        });
        tier5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobTier5", b);


                RadarSettings.getInstance().mobTiers[4] = b;
                editor.apply();


            }
        });
        tier6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobTier6", b);


                RadarSettings.getInstance().mobTiers[5] = b;
                editor.apply();

            }
        });

        tier7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobTier7", b);


                RadarSettings.getInstance().mobTiers[6] = b;
                editor.apply();

            }
        });

        tier8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                editor.putBoolean("mobTier8", b);


                RadarSettings.getInstance().mobTiers[7] = b;
                editor.apply();

            }
        });


        enchant0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobEnchant0", b);


                RadarSettings.getInstance().mobEnchants[0] = b;
                editor.apply();

            }
        });

        enchant1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                editor.putBoolean("mobEnchant1", b);


                RadarSettings.getInstance().mobEnchants[1] = b;
                editor.apply();

            }
        });


        enchant2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobEnchant2", b);


                RadarSettings.getInstance().mobEnchants[2] = b;
                editor.apply();

            }
        });

        enchant3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobEnchant3", b);


                RadarSettings.getInstance().mobEnchants[2] = b;
                editor.apply();

            }
        });
        enchant4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                editor.putBoolean("mobEnchant4", b);


                RadarSettings.getInstance().mobEnchants[4] = b;
                editor.apply();

            }
        });



        showHp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                editor.putBoolean("mobHp", b);


                RadarSettings.getInstance().mobHp = b;
                editor.apply();

            }
        });

        harvestable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobHarvestable", b);


                RadarSettings.getInstance().mobHarvestable = b;
                editor.apply();


            }
        });


        skinnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobSkinnable", b);


                RadarSettings.getInstance().mobSkinnable = b;
                editor.apply();


            }
        });


        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("mobOther", b);


                RadarSettings.getInstance().mobOther = b;
                editor.apply();


            }
        });

        boss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                editor.putBoolean("mobBoss", b);


                RadarSettings.getInstance().mobBoss = b;
                editor.apply();

            }
        });


    }


  
}
