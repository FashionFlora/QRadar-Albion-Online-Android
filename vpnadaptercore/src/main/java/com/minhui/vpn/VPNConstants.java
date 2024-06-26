package com.minhui.vpn;

import android.os.Environment;

/**
 * Created by minhui.zhu on 2017/6/24.
 * Copyright © 2017年 minhui.zhu. All rights reserved.
 */

public interface VPNConstants
{
    int BUFFER_SIZE = 2560;
    String BASE_DIR = Environment.getExternalStorageDirectory() + "/VpnCapture/Conversation/";
    String DATA_DIR = BASE_DIR + "data/";
    String CONFIG_DIR=BASE_DIR+"config/";
    String VPN_SP_NAME="vpn_sp_name";
    String IS_UDP_NEED_SAVE="isUDPNeedSave";
}
