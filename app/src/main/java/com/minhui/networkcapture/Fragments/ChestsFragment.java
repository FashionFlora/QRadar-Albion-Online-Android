package com.minhui.networkcapture.Fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import androidx.fragment.app.Fragment;
import com.minhui.networkcapture.R;
import com.minhui.networkcapture.RadarView.RadarSettings;

public class ChestsFragment extends Fragment
{
    CheckBox chestGreen;
    CheckBox chestBlue;
    CheckBox chestPurple;
    CheckBox chestLegendary;

    public ChestsFragment(View v)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        chestGreen = v.findViewById(R.id.chestGreen);
        chestBlue = v.findViewById(R.id.chestBlue);
        chestPurple = v.findViewById(R.id.chestPurple);
        chestLegendary = v.findViewById(R.id.chestLegendary);

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