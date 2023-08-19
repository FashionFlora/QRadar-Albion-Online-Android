package com.minhui.networkcapture.RadarActivities;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.minhui.networkcapture.Fragments.ChestsFragment;
import com.minhui.networkcapture.Fragments.HarvestingFragment;
import com.minhui.networkcapture.Fragments.MobsFragment;
import com.minhui.networkcapture.Fragments.PvPFragment;
import com.minhui.networkcapture.Fragments.SettingsFragment;
import com.minhui.networkcapture.R;
import com.minhui.networkcapture.RadarDrawView;
import com.minhui.networkcapture.RadarView.RadarView;
import com.minhui.networkcapture.VPNCaptureActivity;

public class RadarFloatingActivity {


    LayoutInflater layoutInflater;

    private LinearLayout contentLayout;

    private void switchContent(int layoutId) {
        contentLayout.removeAllViews();
        View contentView = layoutInflater.inflate(layoutId, contentLayout, false);
        contentLayout.addView(contentView);
    }


    public RadarFloatingActivity(View view, LayoutInflater layoutInflater, RadarView radarView, RadarDrawView radarDrawView)
    {

        this.layoutInflater = layoutInflater;




        TextView tab1 = view.findViewById(R.id.tab1);
        TextView tab2 = view.findViewById(R.id.tab2);
        TextView tab3 = view.findViewById(R.id.tab3);
        TextView tab4 = view.findViewById(R.id.tab4);
        TextView tab5 = view.findViewById(R.id.tab5);

        contentLayout = view.findViewById(R.id.content_layout);



        switchContent(R.layout.fragment_pv_p);


        new PvPFragment(contentLayout );

        tab1.setBackgroundColor(view.getContext().getColor(R.color.dashboard));
        tab2.setBackgroundColor(view.getContext().getColor(R.color.background));
        tab3.setBackgroundColor(view.getContext().getColor(R.color.background));
        tab4.setBackgroundColor(view.getContext().getColor(R.color.background));
        tab5.setBackgroundColor(view.getContext().getColor(R.color.background));



        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchContent(R.layout.fragment_pv_p);

                tab1.setBackgroundColor(view.getContext().getColor(R.color.dashboard));
                tab2.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab3.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab4.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab5.setBackgroundColor(view.getContext().getColor(R.color.background));
                new PvPFragment(contentLayout );
            }
        });


        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchContent(R.layout.fragment_harvesting);

                tab1.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab2.setBackgroundColor(view.getContext().getColor(R.color.dashboard));
                tab3.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab4.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab5.setBackgroundColor(view.getContext().getColor(R.color.background));

                new HarvestingFragment(contentLayout );


            }
        });



        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchContent(R.layout.fragment_mobs);


                tab1.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab2.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab3.setBackgroundColor(view.getContext().getColor(R.color.dashboard));
                tab4.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab5.setBackgroundColor(view.getContext().getColor(R.color.background));

                new MobsFragment(contentLayout);


            }
        });
        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchContent(R.layout.fragment_chests);


                tab1.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab2.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab3.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab4.setBackgroundColor(view.getContext().getColor(R.color.dashboard));
                tab5.setBackgroundColor(view.getContext().getColor(R.color.background));

                new ChestsFragment(contentLayout);

            }
        });


        tab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchContent(R.layout.fragment_settings);


                tab1.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab2.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab3.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab4.setBackgroundColor(view.getContext().getColor(R.color.background));
                tab5.setBackgroundColor(view.getContext().getColor(R.color.dashboard));

                new SettingsFragment(contentLayout, radarView , radarDrawView);

            }
        });
    }
}