package com.minhui.networkcapture.RadarView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.minhui.networkcapture.R;
import com.minhui.vpn.Handlers.HandlerItem.Harvestable;
import com.minhui.vpn.Handlers.HandlerItem.HarvestableType;
import com.minhui.vpn.Handlers.MainHandler;

import java.util.ArrayList;

public class HarvestingDraw {


    View view;
    float[] tempPos = new float[2];


    Paint paintHarvestingSize;
    public void init(View view)
    {
        this.view  =view;
        tempPos[0]=0;
        tempPos[1]=0;


        paintHarvestingSize = new Paint();

        paintHarvestingSize.setColor(view.getContext().getColor(R.color.colorHarvestingSize));
        paintHarvestingSize.setStyle(Paint.Style.FILL);
        paintHarvestingSize.setTextAlign(Paint.Align.CENTER);
        paintHarvestingSize.setTextSize(RadarSettings.getInstance().harvestingIconTextSizeBar);


    }


    public void setTextSize(int size)
    {
        paintHarvestingSize.setTextSize(size);
    }

    public void draw(Canvas canvas, float lpX , float lpY , Matrix transformationMatrix , BitmapCache bitmapCache)
    {






        ArrayList<Harvestable> harvestables = MainHandler.getInstance().harvestablesHandler.getHarvestableList();

        int desiredWidth = RadarSettings.getInstance().harvestingWidthHeightBar;
        int desiredHeight = RadarSettings.getInstance().harvestingWidthHeightBar;

        int healthOffset = RadarSettings.getInstance().harvestingIconTextGapBar;

        for (Harvestable h: harvestables) {


            if(h.getSize()<=0)
            {
                continue;
            }

            if(!RadarSettings.getInstance().harvestingTiers[h.getTier()]){

                continue;
            }

            if(!RadarSettings.getInstance().harvestingEnchants[h.getCharges()]){

                continue;
            }
            int typeIndex = h.getType();
            if (typeIndex >= 0 && typeIndex < HarvestableType.values().length)
            {
                if(!RadarSettings.getInstance().isInHarvestable(   HarvestableType.values()[typeIndex]))
                {
                    continue;

                }


            }
            else
            {
                continue;
            }

            float enemyX =  h.getPosX()*-1+ lpX;
            float enemyY =  h.getPosY()- lpY;

            tempPos[0] = enemyX;
            tempPos[1] = enemyY;

            transformationMatrix.mapPoints(tempPos);
            String name = "";
            try
            {
                 name = HarvestableType.values()[typeIndex].name().toLowerCase();
            }
            catch (Exception ignored){};

            if(name.length()==0)
            {
                continue;

            }


            String totalName = "";


            if (name.contains("fiber"))
            {

                totalName = "fiber_"+ h.getTier() + "_" + h.getEnchant();
            }
            else if(name.contains("ore"))
            {
                totalName = "ore_"+ h.getTier() + "_" + h.getEnchant();
            }
            else if(name.contains("wood"))
            {
                totalName = "logs_"+ h.getTier() + "_" + h.getEnchant();
            }
            else if(name.contains("hide")){

                totalName = "hide_"+ h.getTier() + "_" + h.getEnchant();
            }
            else {
                totalName = "rock_"+ h.getTier() + "_" + h.getEnchant();

            }



            Bitmap bitmap = bitmapCache.getBitmapFromMemCache(totalName);
            if(bitmap == null)
            {




                int resourceId = view.getResources().getIdentifier(totalName, "drawable", view.getContext().getPackageName());
                Drawable drawable = ContextCompat.getDrawable(view.getContext(), resourceId);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                bitmap = bitmapDrawable.getBitmap();
                bitmapCache.addBitmapToMemoryCache(totalName,bitmap);


            }

            int offsetX = (int) (tempPos[0] - (desiredWidth / 2));
            int offsetY = (int) (tempPos[1] - (desiredHeight / 2));
            bitmap= Bitmap.createScaledBitmap(bitmap, desiredWidth, desiredHeight, false);
            canvas.drawBitmap(bitmap , offsetX , offsetY,null);



            if(RadarSettings.getInstance().harvestingSize)
            {

                canvas.drawText(String.valueOf(h.getSize()), tempPos[0], tempPos[1]+healthOffset, paintHarvestingSize);

            }


        }





    }
}
