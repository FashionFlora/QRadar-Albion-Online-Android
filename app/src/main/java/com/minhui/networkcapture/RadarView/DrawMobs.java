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
import com.minhui.vpn.Handlers.HandlerItem.Mob;
import com.minhui.vpn.Handlers.HandlerUtils;
import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.enumerations.MobCodes;

import java.util.ArrayList;

public class DrawMobs
{
    ArrayList<Mob> mobList;
    Paint paintMobCircle;
    Paint paintMobHp;
    Paint paintTreasure;
    Paint paintLimelight;
    Paint paintMobCircleGreen;
    Paint paintMobCircleBlue;
    Paint paintMobCirclePurple;
    Paint paintMobCircleLegendary;
    float[] tempPos = new float[2];
    View view;

    public void init(View view)
    {
        this.view = view;

        paintMobCircle= new Paint();
        paintMobHp = new Paint();
        paintTreasure= new Paint();

        paintLimelight = new Paint();

        paintMobCircleGreen =new Paint();
        paintMobCircleBlue = new Paint();
        paintMobCirclePurple = new Paint();
        paintMobCircleLegendary = new Paint();

        paintTreasure.setColor(view.getContext().getColor(R.color.colorAccent));

        paintMobHp.setColor(view.getContext().getColor(R.color.colorMobHp));
        paintMobHp.setStyle(Paint.Style.FILL);
        paintMobHp.setTextAlign(Paint.Align.CENTER);

        paintLimelight.setColor(view.getContext().getColor(R.color.colorBlueSky));

        paintMobCircle.setColor(view.getContext().getColor(R.color.colorMobCircle));
        paintMobCircle.setStyle(Paint.Style.FILL);
        paintMobCircle.setTextAlign(Paint.Align.CENTER);

        paintMobCircleGreen.setColor(view.getContext().getColor(R.color.colorGreen));
        paintMobCircleBlue.setColor(view.getContext().getColor(R.color.colorBlue));
        paintMobCirclePurple.setColor(view.getContext().getColor(R.color.colorPurple));
        paintMobCircleLegendary.setColor(view.getContext().getColor(R.color.colorLegendary));

        tempPos[0] = 0;
        tempPos[1] = 0;
    }

    public void setTextSize(int size)
    {
        paintMobHp.setTextSize(size);
    }

    public void draw(Canvas canvas, float lpX, float lpY, Matrix transformationMatrix , BitmapCache bitmapCache)
    {
        mobList =(MainHandler.getInstance().mobsHandler.getMobList());
        int desiredWidth = RadarSettings.getInstance().mobIconWidthHeightBar;
        int desiredHeight = RadarSettings.getInstance().mobIconWidthHeightBar;

        int circleRadius = RadarSettings.getInstance().mobRadarSizeBar;
        int textGap = RadarSettings.getInstance().mobTextGapBar;

        for (Mob m: mobList)
        {
            MobCodes type = m.getType();
            boolean continueLoop = false;

            switch (type)
            {
                case Boss:
                {
                    if(!RadarSettings.getInstance().mobBoss)
                        continueLoop = true;
                    break;
                }
                case Enemy:
                {
                    if(!RadarSettings.getInstance().mobOther)
                        continueLoop = true;
                    break;
                }
                case MistPortal:
                {
                    if (!RadarSettings.getInstance().mobOther)
                        continueLoop = true;
                    break;
                }
                case Harvestable:
                {
                    if (RadarSettings.getInstance().mobHarvestable)
                    {
                        if (!RadarSettings.getInstance().mobEnchants[m.getEnchantmentLevel()] || !RadarSettings.getInstance().mobTiers[m.getTier() - 1])
                        {
                            continueLoop = true;
                        }
                    }
                    else
                    {
                        continueLoop = true;
                    }
                    break;
                }
                case Skinnable:
                {
                    if(RadarSettings.getInstance().mobSkinnable)
                    {
                        if(!RadarSettings.getInstance().mobEnchants[m.getEnchantmentLevel()] ||!RadarSettings.getInstance().mobTiers[m.getTier()-1])
                        {
                            continueLoop = true;
                        }
                    }
                    else
                    {
                        continueLoop = true;
                    }

                    break;
                }
            }

            if(continueLoop)
            {
                continue;
            }

            float mobX =  m.getPosX()*-1+ lpX;
            float mobY =  m.getPosY()- lpY;

            tempPos[0] = mobX;
            tempPos[1] = mobY;

            transformationMatrix.mapPoints(tempPos);

            if(type==MobCodes.Enemy)
            {
                switch (m.getEnchantmentLevel())
                {
                    case 1:
                        canvas.drawCircle(tempPos[0], tempPos[1], (float) (circleRadius*1.5),paintMobCircleGreen);
                        break;

                    case 2:
                        canvas.drawCircle(tempPos[0], tempPos[1], (float) (circleRadius*1.5),paintMobCircleBlue);
                        break;

                    case 3:
                        canvas.drawCircle(tempPos[0], tempPos[1], (float) (circleRadius*1.5),paintMobCirclePurple);
                        break;

                    case 4:
                        canvas.drawCircle(tempPos[0], tempPos[1], (float) (circleRadius*1.5),paintMobCircleLegendary);
                        break;
                }

                canvas.drawCircle(tempPos[0], tempPos[1], circleRadius,paintMobCircle);
            }
            else if(type == MobCodes.Boss)
            {
                Bitmap bitmap = bitmapCache.getBitmapFromMemCache(m.getName());
                String  name = m.getName();

                if(bitmap == null)
                {
                    Log.d("Boss nameBitmap", ""+name);

                    int resourceId = view.getResources().getIdentifier(name, "drawable", view.getContext().getPackageName());
                    Drawable drawable = ContextCompat.getDrawable(view.getContext(), resourceId);
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    bitmap = bitmapDrawable.getBitmap();

                    bitmapCache.addBitmapToMemoryCache(name,bitmap);
                }

                int offsetX = (int) (tempPos[0] - (desiredWidth / 2));
                int offsetY = (int) (tempPos[1] - (desiredHeight / 2));

                bitmap= Bitmap.createScaledBitmap(bitmap, desiredWidth, desiredHeight, false);
                canvas.drawBitmap(bitmap , offsetX , offsetY,null);
            }
            else if(type == MobCodes.MistPortal)
            {
                int Rarity = m.getRarity();

                String name = "mist_" + m.getRarity();
                Log.d("Mists nameBitmap", ""+name);

                Bitmap bitmap = bitmapCache.getBitmapFromMemCache(name);

                if(bitmap == null)
                {
                    int resourceId = view.getResources().getIdentifier(name, "drawable", view.getContext().getPackageName());
                    Drawable drawable = ContextCompat.getDrawable(view.getContext(), resourceId);
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    bitmap = bitmapDrawable.getBitmap();
                    bitmapCache.addBitmapToMemoryCache(name,bitmap);
                }

                int desiredWidthNew = (int)(desiredWidth * 0.70);
                int desiredHeightNew = (int)(desiredHeight * 0.70);

                int offsetX = (int) (tempPos[0] - (desiredWidthNew / 2));
                int offsetY = (int) (tempPos[1] - (desiredHeightNew / 2));

                bitmap= Bitmap.createScaledBitmap(bitmap, desiredWidthNew, desiredHeightNew, false);
                canvas.drawBitmap(bitmap , offsetX , offsetY,null);
            }
            else if(type == MobCodes.Events)
            {
                canvas.drawCircle(tempPos[0], tempPos[1], circleRadius, paintMobCircleGreen);
            }
            else if(type == MobCodes.Guard)
            {
                canvas.drawCircle(tempPos[0], tempPos[1], circleRadius, paintTreasure);
            }
            else
            {
                String  name = m.getName() + "_" + m.getTier() + "_" + m.getEnchantmentLevel();
                Log.d("Skinnable-Harvestable  nameBitmap", ""+name);

                Bitmap bitmap = bitmapCache.getBitmapFromMemCache(name);

                if(bitmap == null)
                {
                    int resourceId = view.getResources().getIdentifier(name, "drawable", view.getContext().getPackageName());
                    Drawable drawable = ContextCompat.getDrawable(view.getContext(), resourceId);
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    bitmap = bitmapDrawable.getBitmap();
                    bitmapCache.addBitmapToMemoryCache(name,bitmap);
                }

                int offsetX = (int) (tempPos[0] - (desiredWidth / 2));
                int offsetY = (int) (tempPos[1] - (desiredHeight / 2));

                bitmap= Bitmap.createScaledBitmap(bitmap, desiredWidth, desiredHeight, false);
                canvas.drawBitmap(bitmap , offsetX , offsetY,null);
            }

            if(RadarSettings.getInstance().mobHp)
            {
                String HP = m.getHealth() + "";

                if (!m.info || type == MobCodes.Harvestable || type == MobCodes.Skinnable)
                {
                    HP += " - " + m.getTypeId();
                }
                else  if(type == MobCodes.MistPortal)
                {
                    HP = m.getTypeId() + "";
                }

                canvas.drawText(HP,tempPos[0],tempPos[1]+textGap,paintMobHp);
            }
        }
    }
}
