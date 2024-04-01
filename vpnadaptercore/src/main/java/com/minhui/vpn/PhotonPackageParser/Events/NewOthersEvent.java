package com.minhui.vpn.PhotonPackageParser.Events;

import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;
import java.util.HashMap;

public class NewOthersEvent
{
    public  NewOthersEvent()
    {

    }

    public void NewExit(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
        float[] pos =  Utils.getFloats(parameters.get(2));
    }

    public void NewLoot(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
        String nickname = Utils.getString(parameters.get(3));
        float[] pos =  Utils.getFloats(parameters.get(4));
    }

    public void NewRandomDungeonExit(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
        float[] pos =  Utils.getFloats(parameters.get(1));
        String type = Utils.getString(parameters.get(3));
    }
}
