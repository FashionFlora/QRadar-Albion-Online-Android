package com.minhui.vpn.PhotonPackageParser.Events;

import android.util.Log;
import java.util.HashMap;

import com.google.gson.Gson;
import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;

public class NewMobEvent
{
    final Gson gson = new Gson();
    public NewMobEvent()
    {

    }

    public void MobChangeState(HashMap<Object, Object> parameters)
    {
        int mobId =  Utils.getNumber(parameters.get(0));
        int enchantmentLevel = Utils.getNumber(parameters.get(1));

        MainHandler.getInstance().mobsHandler.UpdateMobEnchantmentLevel(mobId, enchantmentLevel);
    }

    public void NewMob(HashMap<Object, Object> parameters)
    {
        int id = Utils.getNumber(parameters.get(0));
        int typeId = Utils.getNumber(parameters.get(1));
        float[] positionArray = Utils.getFloats(parameters.get(7));
        float health = Utils.getFloat(parameters.get(13));
        int enchant =  Utils.getNumber(parameters.get(31));
        String name =  Utils.getString(parameters.get(32));
        int rarity = Utils.getNumber(parameters.get(33));

        /*
        if (typeId>=79 && typeId <= 84)
        {
            String JsonData = gson.toJson(parameters);
            Log.d("AddMob1",JsonData);
        }
        else if(health == 0)
        {
            String JsonData = gson.toJson(parameters);
            HyperLog.d("AddMob2", JsonData);
        }
        else if(health == 1)
        {
            String JsonData = gson.toJson(parameters);
            HyperLog.d("AddMob3",JsonData);
        }
        else if(name != null && name != "null")
        {

        }
        */

        MainHandler.getInstance().mobsHandler.AddMob(id, typeId, name, positionArray[0], positionArray[1], (int)health, enchant, rarity);
    }

    public void updateMobPosition(int id, float positionX, float positionY)
    {
        MainHandler.getInstance().mobsHandler.UpdateMobPosition(id, positionX, positionY);
    }

    public void removeMob(HashMap<Object, Object> parameters)
    {
        MainHandler.getInstance().mobsHandler.RemoveMob(Utils.getNumber(parameters.get(0)));
    }
}