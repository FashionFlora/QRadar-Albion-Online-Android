package com.minhui.vpn.utils;

import com.minhui.vpn.VPNLog;
import com.minhui.vpn.utils.ThreadProxy;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/**
 * @author minhui.zhu
 *         Created by minhui.zhu on 2018/5/7.
 *         Copyright © 2017年 Oceanwing. All rights reserved.
 */

public class TcpDataSaveHelper
{
    private String dir;

    public TcpDataSaveHelper(String dir) {
        this.dir = dir;
    }

    public void addData(final SaveData data)
    {
        ThreadProxy.getInstance().execute(() ->
        {

        });

    }

    public static class SaveData
    {
        boolean isRequest;
        byte[] needParseData;
        int offSet;
        int playoffSize;

        private SaveData(Builder builder)
        {
            isRequest = builder.isRequest;
            needParseData = builder.needParseData;
            offSet = builder.offSet;
            playoffSize = builder.length;
        }

        public static final class Builder
        {
            private boolean isRequest;
            private byte[] needParseData;
            private int offSet;
            private int length;

            public Builder()
            {

            }

            public Builder isRequest(boolean val)
            {
                isRequest = val;
                return this;
            }

            public Builder needParseData(byte[] val)
            {
                needParseData = val;
                return this;
            }

            public Builder offSet(int val)
            {
                offSet = val;
                return this;
            }

            public Builder length(int val)
            {
                length = val;
                return this;
            }

            public SaveData build() {
                return new SaveData(this);
            }
        }
    }
}
