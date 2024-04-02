package com.minhui.networkcapture.RadarView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.minhui.networkcapture.R;
import com.minhui.vpn.Handlers.HandlerItem.Player;
import com.minhui.vpn.Handlers.MainHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class RadarView extends View
{
    Paint paintCenterObject;
    Matrix transformationMatrix;
    public DrawPlayers drawPlayers = new DrawPlayers();
    public DrawMobs drawMobs = new DrawMobs();
    public HarvestingDraw harvestingDraw = new HarvestingDraw();
    public DrawChests drawChests = new DrawChests();
    public FishingZoneDraw fishingZoneDraw = new FishingZoneDraw();

    Paint borderPaint;
    BitmapCache bitmapCache = new BitmapCache();

    @Subscribe public void onMessage(String event)
    {
        //  Log.d("draw","call");
       invalidate();
    }

    public void init()
    {
        EventBus.getDefault().register(this);
        paintCenterObject =new Paint();
        drawPlayers.init(this);
        drawMobs.init(this);
        harvestingDraw.init(this);
        fishingZoneDraw.init(this);
        drawChests.init(this);

        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(RadarSettings.getInstance().radarCircleSquareBorderBar);
        borderPaint.setColor(getContext().getColor(R.color.colorBlue));

        paintCenterObject.setColor(getContext().getColor(R.color.colorPrimary));
        paintCenterObject.setStyle(Paint.Style.FILL);

        this.post(() -> initMatrix());
    }
    public void setBorderSize(int size)
    {
        borderPaint.setStrokeWidth(size);
    }

    public  void initMatrix()
    {
        transformationMatrix  = new Matrix();

        float radarCenterX = getWidth() / 2f;
        float radarCenterY = getHeight() / 2f;

        transformationMatrix.postTranslate(radarCenterX, radarCenterY);
        transformationMatrix.postRotate(225,radarCenterX,radarCenterY);
        transformationMatrix.postScale(RadarSettings.getInstance().radarScaleBar,RadarSettings.getInstance().radarScaleBar,radarCenterX,radarCenterY);
    }

    public RadarView(Context context)
    {
        super(context);
        init();
    }

    public RadarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    ArrayList<Player> playerList;

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas)
    {
      //  super.onDraw(canvas);

        if(transformationMatrix==null)
        {
            return;
        }

        float lpX = MainHandler.getInstance().playersHandler.localPlayerPosX();
        float lpY = MainHandler.getInstance().playersHandler.localPlayerPosY();

        if(!RadarSettings.getInstance().radarShowTopMost)
        {
            canvas.drawCircle(getWidth()/2,getHeight()/2, RadarSettings.getInstance().radarMiddleCircleBar, paintCenterObject );
        }

        if(RadarSettings.getInstance().radarShowSquare)
        {
            canvas.drawRect(0, 0, getWidth(), getHeight(), borderPaint);
        }

        if(RadarSettings.getInstance().radarShowCircle)
        {
            canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2 -  RadarSettings.getInstance().radarMiddleCircleBar  , borderPaint);
        }

        harvestingDraw.draw(canvas,lpX,lpY,transformationMatrix, bitmapCache);
        fishingZoneDraw.draw(canvas,lpX,lpY,transformationMatrix, bitmapCache);
        drawMobs.draw(canvas,lpX,lpY,transformationMatrix, bitmapCache);
        drawChests.draw(canvas,lpX,lpY,transformationMatrix, bitmapCache);
        drawPlayers.draw(canvas,lpX,lpY,transformationMatrix);

        if(RadarSettings.getInstance().radarShowTopMost)
        {
            canvas.drawCircle(getWidth()/2,getHeight()/2, RadarSettings.getInstance().radarMiddleCircleBar, paintCenterObject );
        }
    }
}
