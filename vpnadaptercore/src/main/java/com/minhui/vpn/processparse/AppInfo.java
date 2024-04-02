package com.minhui.vpn.processparse;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author minhui.zhu
 *         Created by minhui.zhu on 2018/4/30.
 *         Copyright © 2017年 Oceanwing. All rights reserved.
 */

@SuppressWarnings("unchecked")
public class AppInfo implements Serializable
{
    public final String allAppName;
    public final String leaderAppName;
    public final PackageNames pkgs;

    static class Entry
    {
        final String appName;
        final String pkgName;

        public Entry(String appName, String pkgName)
        {
            this.appName = appName;
            this.pkgName = pkgName;
        }
    }

    private AppInfo(String leaderAppName, String allAppName, String[] pkgs)
    {
        this.leaderAppName = leaderAppName;
        this.allAppName = allAppName;
        this.pkgs = PackageNames.newInstance(pkgs);
    }

    public static AppInfo createFromUid(Context ctx, int uid)
    {
        PackageManager pm = ctx.getPackageManager();
        ArrayList<Entry> list = new ArrayList();

        if (uid > 0)
        {
            try
            {
                String[] pkgNames = pm.getPackagesForUid(uid);

                if (pkgNames == null || pkgNames.length <= 0)
                {
                    list.add(new Entry("System", "nonpkg.noname"));
                }
                else
                {
                    for (String pkgName : pkgNames)
                    {
                        if (pkgName != null)
                        {
                            try
                            {
                                PackageInfo appPackageInfo = pm.getPackageInfo(pkgName, 0);
                                String appName = null;

                                if (appPackageInfo != null)
                                {
                                    appName = appPackageInfo.applicationInfo.loadLabel(pm).toString();
                                }

                                if (appName == null || appName.equals(""))
                                {
                                    appName = pkgName;
                                }

                                list.add(new Entry(appName, pkgName));

                            }
                            catch (PackageManager.NameNotFoundException e)
                            {
                                list.add(new Entry(pkgName, pkgName));
                            }
                        }
                    }
                }
            }
            catch (RuntimeException e2)
            {
                Log.i("NRFW", "error getPackagesForUid(). package manager has died");
                return null;
            }
        }
        if (list.size() == 0)
        {
            list.add(new Entry("System", "root.uid=0"));
        }

        Collections.sort(list, (lhs, rhs) ->
        {
            int ret = lhs.appName.compareToIgnoreCase(rhs.appName);

            if (ret == 0)
            {
                return lhs.pkgName.compareToIgnoreCase(rhs.pkgName);
            }

            return ret;
        });

        String[] pkgs = new String[list.size()];
        String[] apps = new String[list.size()];

        for (int i = 0; i < list.size(); i++)
        {
            pkgs[i] = ((Entry) list.get(i)).pkgName;
            apps[i] = ((Entry) list.get(i)).appName;
        }

        return new AppInfo(apps[0], TextUtils.join(",", apps), pkgs);
    }
}
