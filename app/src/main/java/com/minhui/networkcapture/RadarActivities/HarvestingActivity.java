package com.minhui.networkcapture.RadarActivities;

import android.annotation.SuppressLint;
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
import com.minhui.vpn.Handlers.HandlerItem.HarvestableType;

import java.util.Arrays;

public class HarvestingActivity extends AppCompatActivity {

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

    CheckBox fiber;
    CheckBox wood;
    CheckBox ore;
    CheckBox rock;
    CheckBox hide;
    TextView textView;
    Toolbar toolbar;
    
    CheckBox size;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar));
        setContentView(R.layout.activity_harvesting);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        textView = findViewById(R.id.toolbarTitle);
        textView.setText("Harvesting Settings");


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

        enchant0 = findViewById(R.id.enchant0);
        enchant1 = findViewById(R.id.enchant1);
        enchant2 = findViewById(R.id.enchant2);
        enchant3 = findViewById(R.id.enchant3);
        enchant4 = findViewById(R.id.enchant4);

        fiber = findViewById(R.id.fiber);
        wood = findViewById(R.id.wood);
        ore = findViewById(R.id.ore);
        rock = findViewById(R.id.rock);
        hide = findViewById(R.id.hide);

        size =findViewById(R.id.harvestingSize);

        tier1.setChecked(RadarSettings.getInstance().harvestingTiers[0]);
        tier2.setChecked(RadarSettings.getInstance().harvestingTiers[1]);
        tier3.setChecked(RadarSettings.getInstance().harvestingTiers[2]);
        tier4.setChecked(RadarSettings.getInstance().harvestingTiers[3]);
        tier5.setChecked(RadarSettings.getInstance().harvestingTiers[4]);
        tier6.setChecked(RadarSettings.getInstance().harvestingTiers[5]);
        tier7.setChecked(RadarSettings.getInstance().harvestingTiers[6]);
        tier8.setChecked(RadarSettings.getInstance().harvestingTiers[7]);

        enchant0.setChecked(RadarSettings.getInstance().harvestingEnchants[0]);
        enchant1.setChecked(RadarSettings.getInstance().harvestingEnchants[0]);
        enchant2.setChecked(RadarSettings.getInstance().harvestingEnchants[1]);
        enchant3.setChecked(RadarSettings.getInstance().harvestingEnchants[2]);
        enchant4.setChecked(RadarSettings.getInstance().harvestingEnchants[3]);

        fiber.setChecked(RadarSettings.getInstance().harvestingFiber);
        wood.setChecked(RadarSettings.getInstance().harvestingWood);
        ore.setChecked(RadarSettings.getInstance().harvestingOre);
        rock.setChecked(RadarSettings.getInstance().harvestingRock);
        hide.setChecked(RadarSettings.getInstance().harvestingHide);

        size.setChecked(RadarSettings.getInstance().harvestingSize);

        tier1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("harvestingTier1",b);
                editor.apply();
                RadarSettings.getInstance().harvestingTiers[0] =b;



            }
        });


        tier2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("harvestingTier2",b);
                editor.apply();
                RadarSettings.getInstance().harvestingTiers[1] =b;

            }
        });
        tier3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("harvestingTier3",b);
                editor.apply();
                RadarSettings.getInstance().harvestingTiers[2] =b;

            }
        });
        tier4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("harvestingTier4",b);
                editor.apply();
                RadarSettings.getInstance().harvestingTiers[3] =b;

            }
        });
        tier5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("harvestingTier5",b);
                editor.apply();
                RadarSettings.getInstance().harvestingTiers[4] =b;

            }
        });
        tier6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("harvestingTier6",b);
                editor.apply();
                RadarSettings.getInstance().harvestingTiers[5] =b;

            }
        });

        tier7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("harvestingTier7",b);
                editor.apply();
                RadarSettings.getInstance().harvestingTiers[6] =b;

            }
        });

        tier8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("harvestingTier8",b);
                editor.apply();
                RadarSettings.getInstance().harvestingTiers[7] =b;

            }
        });

        enchant0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {



                editor.putBoolean("harvestingEnchant0",b);
                editor.apply();
                RadarSettings.getInstance().harvestingEnchants[0] =b;


            }
        });

        enchant0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {



                editor.putBoolean("harvestingEnchant0",b);
                editor.apply();
                RadarSettings.getInstance().harvestingEnchants[0] =b;


            }
        });

        enchant1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {



                editor.putBoolean("harvestingEnchant1",b);
                editor.apply();
                RadarSettings.getInstance().harvestingEnchants[1] =b;


            }
        });
        enchant2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                editor.putBoolean("harvestingEnchant2",b);
                editor.apply();
                RadarSettings.getInstance().harvestingEnchants[2] =b;

            }
        });
        enchant3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                editor.putBoolean("harvestingEnchant3",b);
                editor.apply();
                RadarSettings.getInstance().harvestingEnchants[3] =b;

            }
        });
        enchant4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                editor.putBoolean("harvestingEnchant4",b);
                editor.apply();
                RadarSettings.getInstance().harvestingEnchants[4] =b;


            }
        });

        fiber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("harvestingFiber",b);
                editor.apply();
                RadarSettings.getInstance().harvestingFiber=b;

                RadarSettings.getInstance().updateHarvestableType(Arrays.asList(
                        HarvestableType.FIBER,
                        HarvestableType.FIBER_CRITTER,
                        HarvestableType.FIBER_GUARDIAN_DEAD,
                        HarvestableType.FIBER_GUARDIAN_RED), b);


            }
        });


        wood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("harvestingWood",b);
                editor.apply();
                RadarSettings.getInstance().harvestingWood=b;

                RadarSettings.getInstance().updateHarvestableType(Arrays.asList(
                        HarvestableType.WOOD,
                        HarvestableType.WOOD_CRITTER_DEAD,
                        HarvestableType.WOOD_CRITTER_GREEN,
                        HarvestableType.WOOD_CRITTER_RED,
                        HarvestableType.WOOD_GIANTTREE,
                        HarvestableType.WOOD_GUARDIAN_RED), b);

            }
        });
        ore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("harvestingOre",b);
                editor.apply();
                RadarSettings.getInstance().harvestingOre =b;


                RadarSettings.getInstance().updateHarvestableType(Arrays.asList(
                        HarvestableType.ORE,
                        HarvestableType.ORE_CRITTER_DEAD,
                        HarvestableType.ORE_CRITTER_GREEN,
                        HarvestableType.ORE_CRITTER_RED,
                        HarvestableType.ORE_GUARDIAN_RED), b);

            }
        });

        rock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("harvestingRock",b);
                editor.apply();
                RadarSettings.getInstance().harvestingRock =b;



                RadarSettings.getInstance().updateHarvestableType(Arrays.asList(
                        HarvestableType.ROCK,
                        HarvestableType.ROCK_CRITTER_DEAD,
                        HarvestableType.ROCK_CRITTER_GREEN,
                        HarvestableType.ROCK_CRITTER_RED,
                        HarvestableType.ROCK_GUARDIAN_RED), b);
            }
        });

        hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("harvestingHide",b);
                editor.apply();
                RadarSettings.getInstance().harvestingHide =b;


                RadarSettings.getInstance().updateHarvestableType(Arrays.asList(
                        HarvestableType.HIDE,
                        HarvestableType.HIDE_FOREST,
                        HarvestableType.HIDE_STEPPE,
                        HarvestableType.HIDE_SWAMP,
                        HarvestableType.HIDE_MOUNTAIN,
                        HarvestableType.HIDE_HIGHLAND,
                        HarvestableType.HIDE_CRITTER,
                        HarvestableType.HIDE_GUARDIAN), b);
            }
        });


        size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                editor.putBoolean("harvestingSize",b);
                editor.apply();
                RadarSettings.getInstance().harvestingSize =b;
            }
        });


    }


}
