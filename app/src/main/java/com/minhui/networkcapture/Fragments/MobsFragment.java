package com.minhui.networkcapture.Fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.minhui.networkcapture.R;
import com.minhui.networkcapture.RadarView.RadarSettings;

public class MobsFragment extends Fragment {
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


    public MobsFragment(View view)
    {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
         SharedPreferences.Editor editor = sharedPreferences.edit();


        tier1 = view.findViewById(R.id.tier1);
        tier2 = view.findViewById(R.id.tier2);
        tier3 = view.findViewById(R.id.tier3);
        tier4 = view.findViewById(R.id.tier4);
        tier5 = view.findViewById(R.id.tier5);
        tier6 = view.findViewById(R.id.tier6);
        tier7 = view.findViewById(R.id.tier7);
        tier8 = view.findViewById(R.id.tier8);
        showHp = view.findViewById(R.id.mobHp);

        enchant0 = view.findViewById(R.id.enchant0);
        enchant1 = view.findViewById(R.id.enchant1);
        enchant2 = view.findViewById(R.id.enchant2);
        enchant3 = view.findViewById(R.id.enchant3);
        enchant4 = view.findViewById(R.id.enchant4);


        harvestable = view.findViewById(R.id.mobHarvestable);
        skinnable = view.findViewById(R.id.mobSkinnable);
        other = view.findViewById(R.id.mobOther);
        boss = view.findViewById(R.id.mobBoss);

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