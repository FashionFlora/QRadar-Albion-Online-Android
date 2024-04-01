/*
** Copyright 2015, Mohamed Naufal
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
**     http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/

package com.minhui.networkcapture;

import static com.minhui.vpn.utils.VpnServiceHelper.START_VPN_SERVICE_REQUEST_CODE;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Keep;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.minhui.networkcapture.Fragments.MainFragment;
import com.minhui.networkcapture.RadarView.RadarSettings;
import com.minhui.vpn.ProxyConfig;
import com.minhui.vpn.utils.DebugLog;
import com.minhui.vpn.utils.VpnServiceHelper;

import java.util.ArrayList;

@Keep
public class VPNCaptureActivity extends FragmentActivity
{
    private static final int REQUEST_STORAGE_PERMISSION = 104;
    private static final int REQUEST_OVERLAY_PERMISSION = 5469;
    public static Context mContext;

    ProxyConfig.VpnStatusListener vpnStatusListener = new ProxyConfig.VpnStatusListener()
    {
        @Override
        public void onVpnStart(Context context)
        {
            handler.post(()->vpnButton.setImageResource(R.mipmap.ic_stop));
            startRadarWindowService();
        }

        @Override
        public void onVpnEnd(Context context)
        {
            handler.post(()->vpnButton.setImageResource(R.mipmap.ic_start));

            if(windowService!=null)
            {
                stopService(windowService);
            }
        }
    };

    private ImageView vpnButton;

    String[] needPermissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    String[] needOverlayPermission = {Manifest.permission.SYSTEM_ALERT_WINDOW};

    private Handler handler;

    @Override
    public void onBackPressed()
    {
        try
        {
            finishAffinity(); // Close all activities

            if(windowService != null)
            {
                stopService(windowService);
            }

            System.exit(0);
        }
        catch (Exception ex)
        {

        }
    }

    TextView days;

    ActivityResultLauncher<Intent> overlayPermissionResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (!Settings.canDrawOverlays(this))
                {
                    // You don't have the permission
                    // Handle the case where user declined the permission
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpn_capture);

        mContext = this;

        /*
        String value = getIntent().getStringExtra("logged");
        float daysAmount = getIntent().getFloatExtra("days",0);
        days = findViewById(R.id.days);

        if(!value.equals("true"))
        {
            Intent intent = new Intent(VPNCaptureActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        if(daysAmount<=0)
        {
            Intent intent = new Intent(VPNCaptureActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        String formattedNumber = decimalFormat.format(daysAmount);

        days.setText("Days left :" + formattedNumber);

        String login = getIntent().getStringExtra("login");
        String password = getIntent().getStringExtra("password");
        Thread t1   = new Thread(() ->{

        while (execution)
        {
            String apiKey = "7psdgdsg3241124123tgcxvczSFD124412D5adfJEBACCHINY44f3265taaFDdgs36ft3546t346XDD5t4128n0HWuTbxz777vfddsgMkDXgdsw3gdsg214erqw";

            LoginDataJSON loginDataJSON = new LoginDataJSON();

            loginDataJSON.name  = login;
            loginDataJSON.password = password;
            String loginJSON = gson.toJson(loginDataJSON);

            HttpTask.Callback callback = new HttpTask.Callback()
            {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onSuccess(String response)
                {

                }

                @Override
                public void onFailure(String errorMessage)
                {
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(VPNCaptureActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    execution = false;
                }
            };

            HttpTask postTask = new HttpTask(apiKey, callback);
            postTask.sendPostRequest(Api.updateUserLoginDate, loginJSON , "PUT");

            try
            {
                Thread.sleep( 20000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        });

        t1.start();
        */

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar));
        vpnButton = (ImageView) findViewById(R.id.vpn);

        vpnButton.setOnClickListener(v ->
        {
          if(VpnServiceHelper.vpnRunningStatus())
          {
              closeVpn();
          }
          else
          {
              startVPN();
          }
        });

        // packageId = (TextView) findViewById(R.id.package_id);

        initMainFragment();
        requestStoragePermission();

        vpnButton.setEnabled(true);
        ProxyConfig.Instance.registerVpnStatusListener(vpnStatusListener);

        if (!Settings.canDrawOverlays(this))
        {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            overlayPermissionResultLauncher.launch(intent);
        }

        MainFragment newFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_vp, newFragment)
                .addToBackStack(null)
                .commit();

        handler = new Handler();
        RadarSettings.getInstance().init(getApplicationContext());
    }
    Intent windowService;

    public  void initMainFragment()
    {

    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    private void startRadarWindowService()
    {
        DebugLog.i("startRadar(%d)\n", 1);

        windowService = new Intent(this, RadarDrawView.class);
        startService(windowService);
    }

    private void requestStoragePermission()
    {
        ActivityCompat.requestPermissions(this, needPermissions, REQUEST_STORAGE_PERMISSION);
    }

    private void closeVpn() {
        VpnServiceHelper.changeVpnRunningStatus(this,false);
    }

    private void startVPN() {
        VpnServiceHelper.changeVpnRunningStatus(this,true);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ProxyConfig.Instance.unregisterVpnStatusListener(vpnStatusListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_OVERLAY_PERMISSION)
        {
            if (Settings.canDrawOverlays(this))
            {
                startRadarWindowService();
            }
            else
            {
                // Overlay permission not granted, handle accordingly
            }
        }

        if (requestCode == START_VPN_SERVICE_REQUEST_CODE && resultCode == RESULT_OK)
        {
            VpnServiceHelper.startVpnService(getApplicationContext());
        }
    }
}
