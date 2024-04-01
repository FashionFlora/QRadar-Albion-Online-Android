package com.minhui.vpn.PhotonPackageParser.Events;

import android.util.Log;

import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;

import java.util.HashMap;

public class NewFishingZoneEvent
{
    public void NewFishingZoneObject(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
        float[] pos =  Utils.getFloats(parameters.get(1));
        int charges = parameters.containsKey(2) ? Utils.getNumber(parameters.get(2)) : 0;
        int fished = parameters.containsKey(3) ? Utils.getNumber(parameters.get(3)) : 0;
        String zoneType = parameters.containsKey(4) ? Utils.getString(parameters.get(4)) : "";

        MainHandler.getInstance().fishingZoneHandler.removeFishingZone(id);
        MainHandler.getInstance().fishingZoneHandler.addFishingZone(id, pos[0], pos[1], charges, fished, zoneType);
    }

    public void removeFishingZoneObject(HashMap<Object, Object> parameters )
    {
        int id = Utils.getNumber(parameters.get(0));
        MainHandler.getInstance().fishingZoneHandler.removeFishingZone(id);
    }

    public void FishingStart(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));

        //Log.d("FishingStart","id: " + id + " Pos:" + pos[0] + "," + pos[1]);
    }

    public void FishingFinished(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));

        //Log.d("FishingStart","id: " + id + " Pos:" + pos[0] + "," + pos[1]);
    }

    public void FishingCatch(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
    }

    public void FishingCast(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
    }

    public void FishingMiniGame(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
    }
}
