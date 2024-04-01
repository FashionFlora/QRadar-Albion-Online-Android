package com.minhui.networkcapture;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.minhui.networkcapture.RadarActivities.RadarFloatingActivity;
import com.minhui.networkcapture.RadarView.RadarSettings;
import com.minhui.networkcapture.RadarView.RadarView;
import com.minhui.vpn.Handlers.MainHandler;

public class RadarDrawView extends Service
{
    private WindowManager windowManager;
    private View overlayView;
    private View overlaySettingsView;
    View radarFloatingSettingsView;
    boolean onLongMove = false;
    int screenWidth =0;
    int screenHeight =0;
    RadarDrawView radarDrawViewPseudoSingleton;
    private static final String CHANNEL_ID = "my_channel_01";

    @Override
    public void onCreate()
    {
        super.onCreate();

        /*

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }

        Intent notificationIntent = new Intent(this, VPNCaptureActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("QRadar service")
                .setContentText("Radar is running...")
                .setSmallIcon(R.drawable.qradarlogo)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
        */

        radarDrawViewPseudoSingleton = this;

        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        screenWidth  = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        showOverlayWindow();
    }

    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "My Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        windowManager.removeView(overlayView);

        if(overlaySettingsView!=null)
        {
            windowManager.removeView(overlaySettingsView);
        }

        if(radarFloatingSettingsView!=null)
        {
            try
            {
                windowManager.removeView(radarFloatingSettingsView);
            }
            catch (Exception e)
            {

            }
        }

        MainHandler.getInstance().clearAll();
    }

    RadarView radarDrawView;
    private boolean showRadarActivitySettings = false;
    private int initialX;
    private int initialY;
    private float initialTouchX;
    private float initialTouchY;
    SharedPreferences sharedPreferences;
    LayoutInflater layoutInflater;
    boolean isLongDragging = false;
    WindowManager.LayoutParams layoutParams;
    WindowManager.LayoutParams layoutParamsSettingsView;

    public void setRadarSize(int size)
    {
        layoutParams.width = size;
        layoutParams.height = size;
        windowManager.updateViewLayout(overlayView,layoutParams);

        radarDrawView.initMatrix();
    }
    WindowManager.LayoutParams layoutParamsSettingsFloatingView;

    public  void setRadarX(int progress)
    {
        layoutParams.x = progress;
        windowManager.updateViewLayout(overlayView,layoutParams);
    }

    public void setRadarY(int progress)
    {
        layoutParams.y = progress;
        windowManager.updateViewLayout(overlayView,layoutParams);
    }

    public void setSettingsHeightPost(int height)
    {
       // layoutParamsSettingsFloatingView.height = height;
       // windowManager.updateViewLayout(radarFloatingSettingsView,height);
    }

    public void setSettingsWidthPost(int widthPost)
    {
        // layoutParamsSettingsFloatingView.width = widthPost;
        // windowManager.updateViewLayout(radarFloatingSettingsView,widthPost);
    }

    public void setSettingsHeight(int progress)
    {
        /*
            layoutParamsSettingsFloatingView.height = progress;
            windowManager.updateViewLayout(radarFloatingSettingsView,layoutParamsSettingsFloatingView);
         */

        ViewGroup.LayoutParams layoutParams =settings_edit.getLayoutParams();
        layoutParams.height = progress;
        settings_edit.setLayoutParams(layoutParams);
    }

    public void setSettingsWidth(int progress)
    {
        //  layoutParamsSettingsFloatingView.width = progress;
        //  windowManager.updateViewLayout(radarFloatingSettingsView,layoutParamsSettingsFloatingView);

        ViewGroup.LayoutParams layoutParams =settings_edit.getLayoutParams();
        layoutParams.width = progress;
        settings_edit.setLayoutParams(layoutParams);
    }

    public void setFloatingX(int progress)
    {
        layoutParamsSettingsView.x = progress;
        windowManager.updateViewLayout(overlaySettingsView,layoutParamsSettingsView);
    }

    public void setFloatingY(int progress)
    {
        layoutParamsSettingsView.y = progress;
        windowManager.updateViewLayout(overlaySettingsView,layoutParamsSettingsView);
    }

    public void setFloatingSize(int progress)
    {
        layoutParamsSettingsView.width = progress;
        layoutParamsSettingsView.height = progress;
        windowManager.updateViewLayout(overlaySettingsView,layoutParamsSettingsView);
    }

    LinearLayout settings_edit;
    LinearLayout content_layout;

    public void reInitMatrix()
    {
        radarDrawView.initMatrix();
    }

    private void showOverlayWindow()
    {
        // Inflate the overlay view
        overlayView = LayoutInflater.from(this).inflate(R.layout.radar_draw_layout, null);
        overlaySettingsView = LayoutInflater.from(this).inflate(R.layout.radar_settings_view, null);
        radarFloatingSettingsView = LayoutInflater.from(this).inflate(R.layout.activity_radar_floating_settings, null);

        settings_edit =  radarFloatingSettingsView.findViewById(R.id.settings_edit);
        content_layout = radarFloatingSettingsView.findViewById(R.id.content_layout);
        layoutInflater=LayoutInflater.from(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();

        layoutParams = new WindowManager.LayoutParams(
                RadarSettings.getInstance().radarSizeWidthHeightBar, RadarSettings.getInstance().radarSizeWidthHeightBar,
                getWindowType(),
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT);

        layoutParams.x = RadarSettings.getInstance().radarXBar;
        layoutParams.y = RadarSettings.getInstance().radarYBar;

        radarDrawView = overlayView.findViewById(R.id.radarView);

        // Add the overlay view to the window manager
        windowManager.addView(overlayView, layoutParams);

        WindowManager.LayoutParams layoutParamsSettingsFloatingView = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, // This type ensures the view overlays on other apps
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, // This flag will allow events to be delivered to the window behind it
                PixelFormat.TRANSLUCENT);

        // Add radarFloatingSettingsView first, so it is below overlaySettingsView
        windowManager.addView(radarFloatingSettingsView, layoutParamsSettingsFloatingView);

        radarFloatingSettingsView.setVisibility(View.GONE);
         new RadarFloatingActivity(radarFloatingSettingsView, layoutInflater , radarDrawView, radarDrawViewPseudoSingleton);

        layoutParamsSettingsView = new WindowManager.LayoutParams(
                RadarSettings.getInstance().floatingWidthHeightBar,
                RadarSettings.getInstance().floatingWidthHeightBar,
                getWindowType(),
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |

                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        layoutParamsSettingsView.x = RadarSettings.getInstance().floatingXBar;
        layoutParamsSettingsView.y = RadarSettings.getInstance().floatingYBar;

        ViewGroup.LayoutParams layoutParams = settings_edit.getLayoutParams();
        layoutParams.height = RadarSettings.getInstance().settingsHeightBar;
        layoutParams.width = RadarSettings.getInstance().settingsWidthBar;
        settings_edit.setLayoutParams(layoutParams);
        windowManager.addView(overlaySettingsView, layoutParamsSettingsView);

        content_layout.setBackgroundColor(Color.argb(RadarSettings.getInstance().settingsTransparencyBar,0,0,0));
        overlaySettingsView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("overlaySettingsView" ,"clicked "+  showRadarActivitySettings);

                showRadarActivitySettings = !showRadarActivitySettings;

                if(showRadarActivitySettings)
                {
                    radarFloatingSettingsView.setVisibility(View.VISIBLE);
                }
                else
                {
                    radarFloatingSettingsView.setVisibility(View.GONE);
                }
            }
        });
    }
    private static final String ANDROID_CHANNEL_ID = "com.minhui.Location.Channel";
    private static final int NOTIFICATION_ID = 555;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }

    private int getWindowType()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            return WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
        else
        {
            return WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
    }

    public void setTransparencySettings(int color)
    {
        content_layout.setBackgroundColor(color);
    }
}