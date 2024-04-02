package com.minhui.networkcapture.RadarActivities;

import static android.widget.CompoundButton.*;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.minhui.networkcapture.BuildConfig;
import com.minhui.networkcapture.R;
import com.minhui.networkcapture.RadarView.RadarSettings;

public class PvPActivity extends AppCompatActivity
{
    CheckBox playerDot;
    CheckBox playerNickname;
    CheckBox playerHealth;
    CheckBox playerMounted;
    CheckBox playerDistance;
    CheckBox playerSound;
    CheckBox playerGuildName;
    Toolbar toolbar;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar));
        setContentView(R.layout.activity_pvp);

        textView = findViewById(R.id.toolbarTitle);
        textView.setText("PvP Settings");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        actionBar.setTitle("");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        playerDot = findViewById(R.id.playerDot);
        playerNickname = findViewById(R.id.playerNickname);
        playerHealth = findViewById(R.id.playerHealth);
        playerMounted = findViewById(R.id.playerMounted);
        playerDistance = findViewById(R.id.playerDistance);
        playerSound = findViewById(R.id.playerSound);
        playerGuildName = findViewById(R.id.playerGuild);

        playerDot.setChecked(RadarSettings.getInstance().playerDot);
        playerNickname.setChecked(RadarSettings.getInstance().playerNickname);
        playerHealth.setChecked(RadarSettings.getInstance().playerHealth);
        playerMounted.setChecked(RadarSettings.getInstance().playerMounted);
        playerDistance.setChecked(RadarSettings.getInstance().playerDistance);
        playerSound.setChecked(RadarSettings.getInstance().playerSound);
        playerGuildName.setChecked(RadarSettings.getInstance().playerGuildName);

        playerDot.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            editor.putBoolean("playerDot",isChecked);
            editor.apply();
            RadarSettings.getInstance().playerDot = isChecked;

            Log.d("playerDot" , " " +sharedPreferences.getBoolean("playerDot", false));
        });

        playerNickname.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            editor.putBoolean("playerNickname",isChecked);
            RadarSettings.getInstance().playerNickname = isChecked;
            editor.apply();
        });

        playerMounted.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            editor.putBoolean("playerMounted",isChecked);
            RadarSettings.getInstance().playerMounted = isChecked;
            editor.apply();
        });

        playerHealth.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            editor.putBoolean("playerHealth",isChecked);
            RadarSettings.getInstance().playerHealth = isChecked;
            editor.apply();
        });

        playerDistance.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            editor.putBoolean("playerDistance",isChecked);
            RadarSettings.getInstance().playerDistance = isChecked;
            editor.apply();
        });

        playerSound.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            editor.putBoolean("playerSound",isChecked);
            RadarSettings.getInstance().playerSound = isChecked;
            editor.apply();
        });

        playerGuildName.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            editor.putBoolean("playerGuildName",isChecked);
            RadarSettings.getInstance().playerGuildName = isChecked;
            editor.apply();
        });
    }
}
