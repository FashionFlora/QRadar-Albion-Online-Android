package com.minhui.networkcapture.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minhui.networkcapture.Adapters.MainDashBoardAdapter;
import com.minhui.networkcapture.R;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {


    RecyclerView dataList;
    List<String> titles;
    List<Integer> images;
    MainDashBoardAdapter adapter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_main, container, false);


        dataList = view.findViewById(R.id.dataList);



        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("PVP");
        titles.add("Harvesting");
        titles.add("Mobs");
        titles.add("Chests");
        titles.add("Settings");

        images.add(R.drawable.pvp);
        images.add(R.drawable.wheat);
        images.add(R.drawable.devil);
        images.add(R.drawable.treasure);
        images.add(R.drawable.setting);
        adapter = new MainDashBoardAdapter(getContext(),titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        dataList.setLayoutManager(gridLayoutManager);

        dataList.setAdapter(adapter);




        return view;

    }
}
