package com.minhui.networkcapture.Fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.minhui.networkcapture.R;
import com.minhui.networkcapture.RadarDrawView;
import com.minhui.networkcapture.RadarView.RadarSettings;
import com.minhui.networkcapture.RadarView.RadarView;
import com.minhui.vpn.Handlers.MainHandler;


public class SettingsFragment extends Fragment {


    TextView pvpCircleLabel;
    TextView pvpCircleGapLabel;
    TextView pvpTextGapLabel;
    TextView pvpTextSizeLabel;

    SeekBar pvpCircleBar;
    SeekBar pvpCircleGapBar;
    SeekBar pvpTextGapBar;
    SeekBar pvpTextSizeBar;


    TextView harvestingWidthHeightLabel;
    TextView harvestingIconTextGapLabel;
    TextView harvestingIconTextSizeLabel;

    SeekBar harvestingWidthHeightBar;
    SeekBar harvestingIconTextGapBar;
    SeekBar harvestingIconTextSizeBar;

    Button resetRadar;


    public  static int currPos = 0;



    public SettingsFragment(View view, RadarView radarView, RadarDrawView radarDrawView) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        resetRadar = view.findViewById(R.id.resetRadar);

        resetRadar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainHandler.getInstance().clearAll();
            }
        });

        pvpCircleLabel = view.findViewById(R.id.pvpCircleLabel);
        pvpCircleGapLabel = view.findViewById(R.id.pvpCircleGapLabel);
        pvpTextGapLabel = view.findViewById(R.id.pvpTextGapLabel);
        pvpTextSizeLabel = view.findViewById(R.id.pvpTextSizeLabel);

        pvpCircleBar = view.findViewById(R.id.pvpCircleBar);
        pvpCircleGapBar = view.findViewById(R.id.pvpCircleGapBar);
        pvpTextGapBar = view.findViewById(R.id.pvpTextGapBar);
        pvpTextSizeBar = view.findViewById(R.id.pvpTextSizeBar);


        pvpCircleLabel.setText("PvP circle size :" + RadarSettings.getInstance().pvpCircleBar);
        pvpCircleBar.setProgress(RadarSettings.getInstance().pvpCircleBar);
        pvpCircleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                RadarSettings.getInstance().pvpCircleBar = progress;
                pvpCircleLabel.setText("PvP circle size :" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Called when the user starts interacting with the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Called when the user stops interacting with the SeekBar


                editor.putInt("pvpCircleBar", seekBar.getProgress());
                editor.apply();

            }
        });



        pvpCircleGapLabel.setText("PvP circle gap :" + RadarSettings.getInstance().pvpCircleGapBar);
        pvpCircleGapBar.setProgress(RadarSettings.getInstance().pvpCircleGapBar);
        pvpCircleGapBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RadarSettings.getInstance().pvpCircleGapBar = progress;
                pvpCircleGapLabel.setText("PvP circle gap :" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {



                editor.putInt("pvpCircleGapBar", seekBar.getProgress());
                editor.apply();

            }
        });



        pvpTextGapLabel.setText("PvP text gap :" + RadarSettings.getInstance().pvpTextGapBar);
        pvpTextGapBar.setProgress(RadarSettings.getInstance().pvpTextGapBar);
        pvpTextGapBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                RadarSettings.getInstance().pvpTextGapBar = progress;
                pvpTextGapLabel.setText("PvP text gap  :" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("pvpTextGapBar", seekBar.getProgress());
                editor.apply();

            }
        });


        pvpTextSizeLabel.setText("PvP  text size :" + RadarSettings.getInstance().pvpTextSizeBar);
        pvpTextSizeBar.setProgress(RadarSettings.getInstance().pvpTextSizeBar);
        pvpTextSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                RadarSettings.getInstance().pvpTextSizeBar = progress;
                pvpTextSizeLabel.setText("PvP  text size :" + progress);

                radarView.drawPlayers.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Called when the user starts interacting with the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Called when the user stops interacting with the SeekBar


                editor.putInt("pvpTextSizeBar", seekBar.getProgress());
                editor.apply();

            }
        });



        harvestingWidthHeightLabel = view.findViewById(R.id.harvestingWidthHeightLabel);
        harvestingIconTextGapLabel = view.findViewById(R.id.harvestingIconTextGapLabel);
        harvestingIconTextSizeLabel = view.findViewById(R.id.harvestingIconTextSizeLabel);

        harvestingWidthHeightBar = view.findViewById(R.id.harvestingWidthHeightBar);
        harvestingIconTextGapBar = view.findViewById(R.id.harvestingIconTextGapBar);
        harvestingIconTextSizeBar = view.findViewById(R.id.harvestingIconTextSizeBar);

        // For harvestingWidthHeightBar:
        harvestingWidthHeightLabel.setText("Harvesting icon size:" + RadarSettings.getInstance().harvestingWidthHeightBar);
        harvestingWidthHeightBar.setProgress(RadarSettings.getInstance().harvestingWidthHeightBar);
        harvestingWidthHeightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RadarSettings.getInstance().harvestingWidthHeightBar = progress;
                harvestingWidthHeightLabel.setText("Harvesting icon size:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt("harvestingWidthHeightBar", seekBar.getProgress());
                editor.apply();
            }
        });

        harvestingIconTextGapLabel.setText("Icon text gap :" + RadarSettings.getInstance().harvestingIconTextGapBar);
        harvestingIconTextGapBar.setProgress(RadarSettings.getInstance().harvestingIconTextGapBar);
        harvestingIconTextGapBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RadarSettings.getInstance().harvestingIconTextGapBar = progress;
                harvestingIconTextGapLabel.setText("Icon text gap :" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("harvestingIconTextGapBar", seekBar.getProgress());
                editor.apply();
            }
        });

        // For harvestingIconTextSizeBar:
        harvestingIconTextSizeLabel.setText("Icon text size :" + RadarSettings.getInstance().harvestingIconTextSizeBar);
        harvestingIconTextSizeBar.setProgress(RadarSettings.getInstance().harvestingIconTextSizeBar);
        harvestingIconTextSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                RadarSettings.getInstance().harvestingIconTextSizeBar = progress;

                radarView.harvestingDraw.setTextSize(RadarSettings.getInstance().harvestingIconTextSizeBar);

                harvestingIconTextSizeLabel.setText("Icon text size :" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt("harvestingIconTextSizeBar", seekBar.getProgress());
                editor.apply();
            }
        });




        TextView mobIconWidthHeightLabel = view.findViewById(R.id.mobIconWidthHeightLabel);
        TextView mobRadarSizeLabel = view.findViewById(R.id.mobRadarSizeLabel);
        TextView mobTextGapLabel = view.findViewById(R.id.mobTextGapLabel);
        TextView mobTextSizeLabel = view.findViewById(R.id.mobTextSizeLabel);

        SeekBar mobIconWidthHeightBar = view.findViewById(R.id.mobIconWidthHeightBar);
        SeekBar mobRadarSizeBar = view.findViewById(R.id.mobRadarSizeBar);
        SeekBar mobTextGapBar = view.findViewById(R.id.mobTextGapBar);
        SeekBar mobTextSizeBar = view.findViewById(R.id.mobTextSizeBar);

        mobIconWidthHeightLabel.setText("Mobs icon size :" + RadarSettings.getInstance().mobIconWidthHeightBar);
        mobIconWidthHeightBar.setProgress(RadarSettings.getInstance().mobIconWidthHeightBar);
        mobIconWidthHeightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                RadarSettings.getInstance().mobIconWidthHeightBar = progress;
                mobIconWidthHeightLabel.setText("Mobs icon size :" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("mobIconWidthHeightBar", seekBar.getProgress());
                editor.apply();
            }
        });

        // For mobRadarSizeBar:
        mobRadarSizeLabel.setText("Circle size :" + RadarSettings.getInstance().mobRadarSizeBar);
        mobRadarSizeBar.setProgress(RadarSettings.getInstance().mobRadarSizeBar);
        mobRadarSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                RadarSettings.getInstance().mobRadarSizeBar = progress;
                mobRadarSizeLabel.setText("Circle size :" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("mobRadarSizeBar", seekBar.getProgress());
                editor.apply();
            }
        });

        // For mobTextGapBar:
        mobTextGapLabel.setText("Text gap :" + RadarSettings.getInstance().mobTextGapBar);
        mobTextGapBar.setProgress(RadarSettings.getInstance().mobTextGapBar);
        mobTextGapBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                RadarSettings.getInstance().mobTextGapBar = progress;
                mobTextGapLabel.setText("Text gap :" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt("mobTextGapBar", seekBar.getProgress());
                editor.apply();
            }
        });

        // For mobTextSizeBar:
        mobTextSizeLabel.setText("Text size :" + RadarSettings.getInstance().mobTextSizeBar);
        mobTextSizeBar.setProgress(RadarSettings.getInstance().mobTextSizeBar);
        mobTextSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                RadarSettings.getInstance().mobTextSizeBar = progress;
                radarView.drawMobs.setTextSize(progress);
                radarView.invalidate();

                mobTextSizeLabel.setText("Text size :" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("mobTextSizeBar", seekBar.getProgress());
                editor.apply();
            }
        });







        TextView chestIconWidthHeightLabel = view.findViewById(R.id.chestIconWidthHeightLabel);

        SeekBar chestIconWidthHeightBar = view.findViewById(R.id.chestIconWidthHeightBar);


        chestIconWidthHeightLabel.setText("Chest icon size :"+RadarSettings.getInstance().chestIconWidthHeightBar);
        chestIconWidthHeightBar.setProgress(RadarSettings.getInstance().chestIconWidthHeightBar);
        chestIconWidthHeightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                RadarSettings.getInstance().chestIconWidthHeightBar = progress;
                chestIconWidthHeightLabel.setText("Chest icon size :"+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("chestIconWidthHeightBar", seekBar.getProgress());
                editor.apply();
            }
        });




        TextView radarScaleLabel = view.findViewById(R.id.radarScaleLabel);
        SeekBar radarScaleBar= view.findViewById(R.id.radarScaleBar);


        int firstTime = (int) (RadarSettings.getInstance().radarScaleBar * 100);
        String text = String.format("%.2f", RadarSettings.getInstance().radarScaleBar);
        radarScaleLabel.setText("Radar distance scale :"+text);
        radarScaleBar.setProgress(firstTime);

        radarScaleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                float totalProgress =  progress/100f;

                radarDrawView.reInitMatrix();
                RadarSettings.getInstance().radarScaleBar = totalProgress;

                radarScaleLabel.setText("Radar distance scale :"+totalProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                float totalProgress = seekBar.getProgress()/100f;

                editor.putFloat("radarScaleBar", totalProgress);
                editor.apply();
            }
        });





        TextView radarSizeWidthHeightLabel = view.findViewById(R.id.radarSizeWidthHeightLabel);

        SeekBar radarSizeWidthHeightBar = view.findViewById(R.id.radarSizeWidthHeightBar);


        radarSizeWidthHeightLabel.setText("Radar size :"+RadarSettings.getInstance().radarSizeWidthHeightBar);
        radarSizeWidthHeightBar.setProgress(RadarSettings.getInstance().radarSizeWidthHeightBar);
        radarSizeWidthHeightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                radarDrawView.setRadarSize(progress);

                RadarSettings.getInstance().radarSizeWidthHeightBar = progress;
                radarSizeWidthHeightLabel.setText("Radar size :"+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("radarSizeWidthHeightBar", seekBar.getProgress());
                editor.apply();
            }
        });


        TextView radarMiddleCircleLabel = view.findViewById(R.id.radarMiddleCircleLabel);
        SeekBar radarMiddleCircleBar = view.findViewById(R.id.radarMiddleCircleBar);


        radarMiddleCircleLabel.setText("Radar middle circle size  :"+RadarSettings.getInstance().radarMiddleCircleBar);
        radarMiddleCircleBar.setProgress(RadarSettings.getInstance().radarMiddleCircleBar);
        radarMiddleCircleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                RadarSettings.getInstance().radarMiddleCircleBar = progress;
                radarMiddleCircleLabel.setText("Radar middle circle size :"+progress);

                radarView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("radarMiddleCircleBar", seekBar.getProgress());
                editor.apply();
            }
        });

        CheckBox radarShowTopMostCheckBox = view.findViewById(R.id.radarShowTopMostCheckBox);

        radarShowTopMostCheckBox.setChecked(RadarSettings.getInstance().radarShowTopMost);


        radarShowTopMostCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                RadarSettings.getInstance().radarShowTopMost = b;
                editor.putBoolean("radarShowTopMost", b);
                editor.apply();

            }
        });

        CheckBox radarShowSquareCheckBox = view.findViewById(R.id.radarShowSquareCheckBox);
        radarShowSquareCheckBox.setChecked(RadarSettings.getInstance().radarShowSquare);

        radarShowSquareCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                RadarSettings.getInstance().radarShowSquare = b;
                editor.putBoolean("radarShowSquare", b);
                editor.apply();
                radarView.invalidate();

            }
        });

        CheckBox radarShowCircleCheckBox = view.findViewById(R.id.radarShowCircleCheckBox);
        radarShowCircleCheckBox.setChecked(RadarSettings.getInstance().radarShowCircle);

        radarShowCircleCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                RadarSettings.getInstance().radarShowCircle = b;
                editor.putBoolean("radarShowCircle", b);
                editor.apply();
                radarView.invalidate();

            }
        });


        TextView radarCircleSquareBorderLabel = view.findViewById(R.id.radarCircleSquareBorderLabel);
        SeekBar radarCircleSquareBorderBar = view.findViewById(R.id.radarCircleSquareBorderBar);



        radarCircleSquareBorderLabel.setText("Radar shapes border size :"+RadarSettings.getInstance().radarCircleSquareBorderBar);
        radarCircleSquareBorderBar.setProgress(RadarSettings.getInstance().radarCircleSquareBorderBar);
        radarCircleSquareBorderBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {




                radarView.setBorderSize((int) progress);


                RadarSettings.getInstance().radarCircleSquareBorderBar = progress;
                radarCircleSquareBorderLabel.setText("Radar shapes border size :"+progress);

                radarView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("radarCircleSquareBorderBar", seekBar.getProgress());
                editor.apply();
            }
        });


        TextView radarXLabel = view.findViewById(R.id.radarXLabel);
        SeekBar radarXBar = view.findViewById(R.id.radarXBar);




        radarXLabel.setText("Radar x :"+RadarSettings.getInstance().radarXBar);
        radarXBar.setProgress(RadarSettings.getInstance().radarXBar);
        radarXBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                radarDrawView.setRadarX(progress);



                RadarSettings.getInstance().radarXBar = progress;
                radarXLabel.setText("Radar x :"+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("radarXBar", seekBar.getProgress());
                editor.apply();
            }
        });




        TextView radarYLabel = view.findViewById(R.id.radarYLabel);
        SeekBar radarYBar = view.findViewById(R.id.radarYBar);


        radarYLabel.setText("Radar y :"+RadarSettings.getInstance().radarYBar);
        radarYBar.setProgress(RadarSettings.getInstance().radarYBar);
        radarYBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                radarDrawView.setRadarY(progress);
                RadarSettings.getInstance().radarYBar = progress;
                radarYLabel.setText("Radar y :"+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("radarYBar", seekBar.getProgress());
                editor.apply();
            }
        });


        TextView settingsWidthLabel = view.findViewById(R.id.settingsWidthLabel);
        SeekBar settingsWidthBar = view.findViewById(R.id.settingsWidthBar);



        settingsWidthLabel.setText("Settings width :"+RadarSettings.getInstance().settingsWidthBar);
        settingsWidthBar.setProgress(RadarSettings.getInstance().settingsWidthBar);
        settingsWidthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                RadarSettings.getInstance().settingsWidthBar = progress;
                settingsWidthLabel.setText("Settings width :"+progress);

                radarDrawView.setSettingsWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("settingsWidthBar", seekBar.getProgress());
                editor.apply();
            }
        });



        TextView settingsHeightLabel = view.findViewById(R.id.settingsHeightLabel);
        SeekBar settingsHeightBar = view.findViewById(R.id.settingsHeightBar);



        settingsHeightLabel.setText("Settings height :"+RadarSettings.getInstance().settingsHeightBar);
        settingsHeightBar.setProgress(RadarSettings.getInstance().settingsHeightBar);
        settingsHeightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                RadarSettings.getInstance().settingsHeightBar = progress;
                settingsHeightLabel.setText("Settings height :"+progress);

                radarDrawView.setSettingsHeight(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("settingsHeightBar", seekBar.getProgress());
                editor.apply();
            }
        });





        TextView floatingWidthHeightLabel  = view.findViewById(R.id.floatingWidthHeightLabel);
        SeekBar floatingWidthHeightBar = view.findViewById(R.id.floatingWidthHeightBar);


        floatingWidthHeightLabel.setText("Floating icon size :"+RadarSettings.getInstance().floatingWidthHeightBar);
        floatingWidthHeightBar.setProgress(RadarSettings.getInstance().floatingWidthHeightBar);
        floatingWidthHeightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                RadarSettings.getInstance().floatingWidthHeightBar = progress;
                floatingWidthHeightLabel.setText("Floating icon size :"+progress);

                radarDrawView.setFloatingSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("floatingWidthHeightBar", seekBar.getProgress());
                editor.apply();
            }
        });


        TextView floatingXLabel = view.findViewById(R.id.floatingXLabel);
        SeekBar floatingXBar = view.findViewById(R.id.floatingXBar);




        floatingXLabel.setText("Floating x :"+RadarSettings.getInstance().floatingXBar);
        floatingXBar.setProgress(RadarSettings.getInstance().floatingXBar);
        floatingXBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                RadarSettings.getInstance().floatingXBar = progress;
                floatingXLabel.setText("Floating x :"+progress);

                radarDrawView.setFloatingX(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("floatingXBar", seekBar.getProgress());
                editor.apply();
            }
        });




        TextView floatingYLabel = view.findViewById(R.id.floatingYLabel);
        SeekBar floatingYBar = view.findViewById(R.id.floatingYBar);




        floatingYLabel.setText("Floating y :"+RadarSettings.getInstance().floatingYBar);
        floatingYBar.setProgress(RadarSettings.getInstance().floatingYBar);
        floatingYBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                RadarSettings.getInstance().floatingYBar = progress;
                floatingYLabel.setText("Floating y :"+progress);


                radarDrawView.setFloatingY(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("floatingYBar", seekBar.getProgress());
                editor.apply();
            }
        });


        TextView settingsTransparencyLabel = view.findViewById(R.id.settingsTransparencyLabel);
        SeekBar settingsTransparencyBar = view.findViewById(R.id.settingsTransparencyBar);


        settingsTransparencyLabel.setText("Settings transparency :"+RadarSettings.getInstance().settingsTransparencyBar);
        settingsTransparencyBar.setProgress(RadarSettings.getInstance().settingsTransparencyBar);
        settingsTransparencyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {




                int color = Color.argb(progress,0,0,0);



                RadarSettings.getInstance().settingsTransparencyBar = progress;
                settingsTransparencyLabel.setText("Settings transparency :"+progress);

                radarDrawView.setTransparencySettings(color);

             //   radarDrawView.setFloatingY(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editor.putInt("settingsTransparencyBar", seekBar.getProgress());
                editor.apply();
            }
        });




    }

}























