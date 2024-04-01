package com.minhui.networkcapture.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minhui.networkcapture.R;
import com.minhui.networkcapture.RadarActivities.ChestsActivity;
import com.minhui.networkcapture.RadarActivities.HarvestingActivity;
import com.minhui.networkcapture.RadarActivities.MobsActivity;
import com.minhui.networkcapture.RadarActivities.PvPActivity;

import java.util.List;

public class MainDashBoardAdapter extends RecyclerView.Adapter<MainDashBoardAdapter.ViewHolder>
{
    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;
    Context mContext;

    public MainDashBoardAdapter(Context ctx, List<String> titles, List<Integer> images)
    {
        this.titles = titles;
        this.images = images;
        this.mContext = ctx;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.item_main_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.title.setText(titles.get(position));
        holder.gridIcon.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            gridIcon = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener(v ->
            {
                switch (getAdapterPosition())
                {
                    case 0:
                        Intent playerPvPActivity = new Intent(mContext, PvPActivity.class);
                        mContext.startActivity(playerPvPActivity);
                        break;

                    case 1:
                        Intent playerHarvestingActivity = new Intent(mContext, HarvestingActivity.class);
                        mContext.startActivity(playerHarvestingActivity);
                        break;

                    case 2:
                        Intent mobsActivity = new Intent(mContext, MobsActivity.class);
                        mContext.startActivity(mobsActivity);
                        break;

                    case 3:
                        Intent chestsActivity = new Intent(mContext, ChestsActivity.class);
                        mContext.startActivity(chestsActivity);
                        break;
                }
            //    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            });
        }
    }
}