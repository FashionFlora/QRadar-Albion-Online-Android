package com.minhui.vpn.utils;

import android.util.Log;

import com.minhui.vpn.VPNConstants;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by minhui.zhu on 2017/6/14.
 * Copyright © 2017年 minhui.zhu. All rights reserved.
 */

public class SocketUtils
{
    private static String TAG = SocketUtils.class.getSimpleName();

    private SocketUtils()
    {

    }

    public static void closeResources(Closeable... resources)
    {
        for (Closeable resource : resources)
        {
            try
            {
                if (resource != null)
                {
                    resource.close();
                }
            }
            catch (Exception e)
            {
                Log.d(TAG, "failed to close resource error is:" + e.getMessage());
            }
        }
    }

    public static ByteBuffer getByteBuffer()
    {
        return ByteBuffer.allocate(VPNConstants.BUFFER_SIZE);
    }
}
