package com.minhui.networkcapture.RadarView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.minhui.vpn.Handlers.HandlerItem.Chest;
import com.minhui.vpn.Handlers.HandlerItem.Mob;
import com.minhui.vpn.Handlers.MainHandler;

import java.util.ArrayList;

public class DrawChests
{
    ArrayList<Chest> chestMist;

    View view;

    float[] tempPos = new float[2];

    public  void init(View view)
    {
        this.view = view;

        tempPos[0]=0;
        tempPos[1]=0;
    }

    public void draw(Canvas canvas, float lpX, float lpY, Matrix transformationMatrix , BitmapCache bitmapCache)
    {
        chestMist =(MainHandler.getInstance().chestHandler.getChests());

        int desiredWidth = RadarSettings.getInstance().chestIconWidthHeightBar;
        int desiredHeight = RadarSettings.getInstance().chestIconWidthHeightBar;

        for (Chest ch: chestMist)
        {
            String name = ch.getName();

            if(!RadarSettings.getInstance().isInChests(name))
            {
                continue;
            }

            String totalName = "";

            if(name.contains("standard"))
            {
                totalName = "standard";
            }
            else if(name.contains("uncommon"))
            {
                totalName = "uncommon";
            }
            else  if(name.contains("rare"))
            {
                totalName = "rare";
            }
            else
            {
                totalName = "legendary";
            }

            float enemyX =  ch.getPosX()*-1+ lpX;
            float enemyY =  ch.getPosY()- lpY;

            tempPos[0] = enemyX;
            tempPos[1] = enemyY;

            transformationMatrix.mapPoints(tempPos);

            Bitmap bitmap = bitmapCache.getBitmapFromMemCache(totalName);

            if (bitmap == null)
            {
                Log.d("Chest nameBitmap", ""+totalName);

                int resourceId = view.getResources().getIdentifier(totalName, "drawable", view.getContext().getPackageName());
                Drawable drawable = ContextCompat.getDrawable(view.getContext(), resourceId);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                bitmap = bitmapDrawable.getBitmap();

                bitmapCache.addBitmapToMemoryCache(totalName, bitmap);
            }

            int offsetX = (int) (tempPos[0] - (desiredWidth / 2));
            int offsetY = (int) (tempPos[1] - (desiredHeight / 2));

            bitmap = Bitmap.createScaledBitmap(bitmap, desiredWidth, desiredHeight, false);
            canvas.drawBitmap(bitmap , offsetX , offsetY,null);
        }
    }
}