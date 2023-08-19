package com.minhui.vpn.PhotonPackageParser.Events;

import android.util.Log;

import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;

import java.util.HashMap;

public class NewMobEvent {



    public NewMobEvent()
    {

    }


    public void updateEnchant(HashMap<Object, Object> parameters) {


        int mobId =  Utils.getNumber(parameters.get(0));
        int enchantmentLevel = Utils.getNumber(parameters.get(1));
        MainHandler.getInstance().mobsHandler.UpdateMobEnchantmentLevel(mobId,enchantmentLevel);

    }

    public void addNewMob(HashMap<Object, Object> parameters) {


        int id = Utils.getNumber(parameters.get(0));
        int typeId = Utils.getNumber(parameters.get(1));

        Log.d("typeID", ""+typeId);
        float hp =0;
        int rarity =1;
        float[] positionArray = Utils.getFloats(parameters.get(7));
        int enchant = 0;

        try{
  //          hp = Utils.getNumber(parameters.get(13));

            hp = Float.parseFloat(parameters.get(13).toString());

        }
        catch(Exception e)
        {

            hp = 0;
        }

        try
        {
            enchant = Utils.getNumber(parameters.get(31));
        }
        catch(Exception ignored){}

        try {

            rarity = Utils.getNumber(parameters.get(19));

        }
        catch(Exception ignored){}

        MainHandler.getInstance().mobsHandler.AddMob(id,typeId,positionArray[0], positionArray[1], (int) hp,enchant,rarity);





    }

    public void updateMobPosition(int id, float positionX, float positionY) {


        MainHandler.getInstance().mobsHandler.UpdateMobPosition(id,positionX,positionY);

    }

    public void removeMob(HashMap<Object, Object> parameters) {

        MainHandler.getInstance().mobsHandler.RemoveMob(Utils.getNumber(parameters.get(0)));
    }
}
