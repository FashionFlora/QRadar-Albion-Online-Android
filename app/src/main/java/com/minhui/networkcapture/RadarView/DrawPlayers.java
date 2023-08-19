package com.minhui.networkcapture.RadarView;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;

import com.minhui.networkcapture.R;
import com.minhui.vpn.Handlers.HandlerItem.Player;
import com.minhui.vpn.Handlers.MainHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DrawPlayers {
    ArrayList<Player> playerList;


    DecimalFormat decimalFormat1Digit = new DecimalFormat("#");

    float[] tempPos = new float[2];

    Paint whitePaint;
    Paint paintMiddle;
    Paint playerPaint;
    Paint healthPaint;


    public void init(View view)
    {
        tempPos[0] = 0;
        tempPos[1] = 0;


        whitePaint = new Paint();
        paintMiddle= new Paint();
        playerPaint= new Paint();
        healthPaint = new Paint();



        whitePaint.setColor(view.getContext().getColor(R.color.white_FFFFFFFF));
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setTextAlign(Paint.Align.CENTER);

        whitePaint.setTextSize(RadarSettings.getInstance().pvpTextSizeBar);

        paintMiddle.setColor(view.getContext().getColor(R.color.colorPrimary));
        paintMiddle.setStyle(Paint.Style.FILL);

        playerPaint.setColor(view.getContext().getColor(R.color.holo_blue_dark));
        playerPaint.setStyle(Paint.Style.FILL);

        healthPaint.setStyle(Paint.Style.FILL);
        healthPaint.setColor(view.getContext().getColor(R.color.healthColor));
        healthPaint.setTextAlign(Paint.Align.CENTER);
        healthPaint.setTextSize(RadarSettings.getInstance().pvpTextSizeBar);
        healthPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

    }

    public  void setTextSize(int textSize)
    {

        whitePaint.setTextSize(textSize);
        healthPaint.setTextSize(textSize);
    }


    public void draw(Canvas canvas , float lpX, float lpY , Matrix transformationMatrix)
    {

        int enemyPlayerCircleRadius = RadarSettings.getInstance().pvpCircleBar;
        int enemyPlayerCircleGap = RadarSettings.getInstance().pvpCircleGapBar;
        int enemyPlayerTextGap = RadarSettings.getInstance().pvpTextGapBar;


        int distancePlayerOffset = 0;


        playerList =(MainHandler.getInstance().playersHandler.getPlayersInRange());



        int i =0;


        for (Player p :playerList) {

            if(i>30)
            {
                break;

            }

            float enemyX =  p.getPosX()*-1+ lpX;
            float enemyY =  p.getPosY()- lpY;

            tempPos[0] = enemyX;
            tempPos[1] = enemyY;

            transformationMatrix.mapPoints(tempPos);




            if(RadarSettings.getInstance().playerDot)
            {

                canvas.drawCircle(tempPos[0], tempPos[1], enemyPlayerCircleRadius,playerPaint);
                distancePlayerOffset+=enemyPlayerCircleGap;
            }

            if(RadarSettings.getInstance().playerMounted)
            {

                if(p.mounted)
                {
                    canvas.drawText("M", tempPos[0], tempPos[1]+enemyPlayerCircleRadius/2, whitePaint);

                }

            }
            if (RadarSettings.getInstance().playerNickname) {
                canvas.drawText(p.getNickname(), tempPos[0], tempPos[1] + distancePlayerOffset, whitePaint);
                distancePlayerOffset+=enemyPlayerTextGap;

            }

            if(RadarSettings.getInstance().playerHealth)
            {



                int top = (int)tempPos[1] + distancePlayerOffset;







             //   canvas.drawRect(new Rect(left,top,right,bottom),healthPaint);
                canvas.drawText(String.valueOf(p.initialHealth), tempPos[0], top, healthPaint);

                distancePlayerOffset+=enemyPlayerTextGap;



            }

            if(RadarSettings.getInstance().playerGuildName){

                if(!p.getGuild().equals("null"))
                {
                    canvas.drawText(p.getGuild(), tempPos[0], tempPos[1] + distancePlayerOffset, whitePaint);
                }


            }









            if(RadarSettings.getInstance().playerDistance)
            {
                float distance = calculateDistance(p.getPosX(), p.getPosY(),lpX,lpY);

                canvas.drawText(decimalFormat1Digit.format(distance), tempPos[0], tempPos[1]  -20, whitePaint);
            }





            distancePlayerOffset=0;

            i+=1;
        }




    }


    public static float calculateDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        return distance;
    }

}
