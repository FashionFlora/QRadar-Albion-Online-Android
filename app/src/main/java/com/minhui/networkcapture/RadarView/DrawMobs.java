package com.minhui.networkcapture.RadarView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.minhui.networkcapture.R;
import com.minhui.vpn.Handlers.HandlerItem.Mob;
import com.minhui.vpn.Handlers.HandlerItem.Player;
import com.minhui.vpn.Handlers.HandlerUtils;
import com.minhui.vpn.Handlers.MainHandler;

import java.util.ArrayList;

public class DrawMobs {

    ArrayList<Mob> mobList;

    Paint paintMobCircle;
    Paint paintMobHp;

    Paint paintMobCircleGreen;
    Paint paintMobCircleBlue;
    Paint paintMobCirclePurple;
    Paint paintMobCircleLegendary;
    float[] tempPos = new float[2];

    View view;

    public  void init(View view){


        this.view = view;

        paintMobCircle= new Paint();
        paintMobHp = new Paint();

        paintMobCircleGreen =new Paint();
        paintMobCircleBlue = new Paint();
        paintMobCirclePurple = new Paint();
        paintMobCircleLegendary = new Paint();


        paintMobHp.setColor(view.getContext().getColor(R.color.colorMobHp));
        paintMobHp.setStyle(Paint.Style.FILL);
        paintMobHp.setTextAlign(Paint.Align.CENTER);

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



    public void draw(Canvas canvas, float lpX, float lpY, Matrix transformationMatrix , BitmapCache bitmapCache) {

        mobList =(MainHandler.getInstance().mobsHandler.getMobList());
        int desiredWidth = RadarSettings.getInstance().mobIconWidthHeightBar;
        int desiredHeight = RadarSettings.getInstance().mobIconWidthHeightBar;

        int circleRadius = RadarSettings.getInstance().mobRadarSizeBar;
        int textGap = RadarSettings.getInstance().mobTextGapBar;




        for (Mob m: mobList) {

            int type = m.getType();

            boolean continueLoop= false;


            switch (type)
            {

                case HandlerUtils.BOSS_MOB:

                    if(!RadarSettings.getInstance().mobBoss)
                        continueLoop = true;
                    break;
                case HandlerUtils.DEFAULT_MOB:
                    if(!RadarSettings.getInstance().mobOther)
                        continueLoop = true;
                    break;
                case HandlerUtils.HARVESTALBE_MOB:



                        if(RadarSettings.getInstance().mobHarvestable)
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
                case HandlerUtils.SKINNABLE_MOB:
                {

                    if(RadarSettings.getInstance().mobSkinnable)
                    {
                        if(!RadarSettings.getInstance().mobEnchants[m.getEnchantmentLevel()] ||!RadarSettings.getInstance().mobTiers[m.getTier()-1])
                        {
                            continueLoop = true;
                        }
                    }
                    else {
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


            if(type==HandlerUtils.DEFAULT_MOB )
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


            else if(type == HandlerUtils.BOSS_MOB)
            {

                Bitmap bitmap = bitmapCache.getBitmapFromMemCache(m.getName());
                String  name = m.getName();
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
            else
            {
                String  name = m.getName() + "_" + m.getTier() + "_" + m.getEnchantmentLevel();

                Log.d("nameBitmap", ""+name);


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

                canvas.drawText(String.valueOf(m.getHealth()),tempPos[0],tempPos[1]+textGap,paintMobHp);

            }






        }
    }
}
