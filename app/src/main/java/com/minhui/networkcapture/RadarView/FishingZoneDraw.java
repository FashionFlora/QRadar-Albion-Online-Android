package com.minhui.networkcapture.RadarView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.minhui.networkcapture.R;
import com.minhui.vpn.Handlers.HandlerItem.FishingZone;
import com.minhui.vpn.Handlers.HandlerItem.Harvestable;
import com.minhui.vpn.Handlers.HandlerItem.HarvestableType;
import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;

import java.util.ArrayList;

public class FishingZoneDraw
{
    View view;
    float[] tempPos = new float[2];

    Paint paintHarvestingSize;
    public void init(View view)
    {
        this.view = view;

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
        ArrayList<FishingZone> fishingZone = MainHandler.getInstance().fishingZoneHandler.getFishingZoneList();

        int desiredWidth = RadarSettings.getInstance().harvestingWidthHeightBar;
        int desiredHeight = RadarSettings.getInstance().harvestingWidthHeightBar;
        int healthOffset = RadarSettings.getInstance().harvestingIconTextGapBar;
        boolean isInHarvestable = RadarSettings.getInstance().harvestingZoneFishing;

        if (isInHarvestable)
        {
            for (FishingZone h: fishingZone)
            {
                if(h.getCharges()<=0)
                {
                    continue;
                }

                float enemyX =  h.getPosX() * -1 + lpX;
                float enemyY =  h.getPosY() - lpY;

                tempPos[0] = enemyX;
                tempPos[1] = enemyY;

                /*
                double Distance = Utils.calculateDistance(lpX, lpY, h.getPosX(), h.getPosY());

                if (Distance > 80)
                {
                    Log.d("FishingZoneDistance","Distance: "  + Distance);
                }
                */

                transformationMatrix.mapPoints(tempPos);

                String totalName =  "fishing_rod";

                Bitmap bitmap = bitmapCache.getBitmapFromMemCache(totalName);

                if(bitmap == null)
                {
                    Log.d("Fishing nameBitmap", ""+totalName);

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
                    canvas.drawText(String.valueOf(h.getCharges()), tempPos[0], tempPos[1]+healthOffset, paintHarvestingSize);
                }
            }
        }
    }
}
