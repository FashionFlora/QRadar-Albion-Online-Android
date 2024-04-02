package com.minhui.vpn.nat;

import com.minhui.vpn.processparse.AppInfo;
import com.minhui.vpn.utils.CommonMethods;
import java.io.Serializable;

/**
 * Created by zengzheying on 15/12/29.
 */

public class NatSession implements Serializable
{
    public static final String TCP = "TCP";
    public static final String UDP = "UPD";

    public String type;
    public String ipAndPort;
    public int remoteIP;
    public short remotePort;
    public String remoteHost;
    public short localPort;
    public int bytesSent;
    public int packetSent;
    public long receiveByteNum;
    public long receivePacketNum;
    public long lastRefreshTime;
    public boolean isHttpsSession;
    public String requestUrl;
    public String pathUrl;
    public String method;
    public AppInfo appInfo;
    public long connectionStartTime = System.currentTimeMillis();
    public long vpnStartTime;
    public boolean isHttp;

    @Override
    public String toString()
    {
        return String.format("%s/%s:%d packet: %d", remoteHost, CommonMethods.ipIntToString(remoteIP),
                remotePort & 0xFFFF, packetSent);
    }

    public String getUniqueName()
    {
        String uinID = ipAndPort + connectionStartTime;
        return String.valueOf(uinID.hashCode());
    }

    public void refreshIpAndPort()
    {
        int remoteIPStr1 = (remoteIP & 0XFF000000) >> 24 & 0XFF;
        int remoteIPStr2 = (remoteIP & 0X00FF0000) >> 16;
        int remoteIPStr3 = (remoteIP & 0X0000FF00) >> 8;
        int remoteIPStr4 = remoteIP & 0X000000FF;

        String remoteIPStr = "" + remoteIPStr1 + ":" + remoteIPStr2 + ":" + remoteIPStr3 + ":" + remoteIPStr4;
        ipAndPort = type + ":" + remoteIPStr + ":" + remotePort + " " + ((int) localPort & 0XFFFF);
    }
}
