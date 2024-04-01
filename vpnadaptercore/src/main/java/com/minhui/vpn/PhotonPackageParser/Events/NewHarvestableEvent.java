package com.minhui.vpn.PhotonPackageParser.Events;

import android.util.Log;

import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;
import com.minhui.vpn.PhotonPackageParser.enumerations.EventCodes;

import java.util.HashMap;

public class NewHarvestableEvent
{
    public void HarvestableChangeState(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
        int charges =  Utils.getNumber(parameters.get(1));
        int enchantment  =  Utils.getNumber(parameters.get(2));

        MainHandler.getInstance().harvestablesHandler.updateHarvestable(id,charges,enchantment);
    }

    public void NewHarvestableObject(HashMap<Object, Object> parameters)
    {
        int id = Utils.getNumber(parameters.get(0));
        int type = Utils.getNumber(parameters.get(5));
        int tier = Utils.getNumber(parameters.get(7));
        float[] location = Utils.getFloats(parameters.get(8));
        int charges = Utils.getNumber(parameters.get(10));;
        int  enchant = Utils.getNumber(parameters.get(11));

        MainHandler.getInstance().harvestablesHandler.removeHarvestable(id);
        MainHandler.getInstance().harvestablesHandler.addHarvestable(id, type, tier, location[0], location[1], charges, enchant);
    }
    public void NewSimpleHarvestableObjectList(HashMap<Object, Object> parameters)
    {
        int[] a0 = Utils.getKnownArray(parameters.get(0));

        if(a0.length==0)
        {
            return;
        }

        if (parameters.size() != 6)
        {
            return;
        }

        int[] a1 = Utils.getKnownArray(parameters.get(1));
        int[] a2 = Utils.getKnownArray(parameters.get(2));
        float[] a3 =  Utils.getFloats(parameters.get(3));
        int[] a4 = Utils.getKnownArray(parameters.get(4));

        for (int i = 0; i < a0.length; i++)
        {
            int id = a0[i];
            int type = a1[i];
            int tier = a2[i];
            float posX = a3[i * 2];
            float posY = a3[i * 2 + 1];
            int charges = a4[i];

            MainHandler.getInstance().harvestablesHandler.removeHarvestable(id);
            MainHandler.getInstance().harvestablesHandler.addHarvestable(id, type, tier, posX, posY, charges,0);
        }
    }
    public void removeHarvestable(HashMap<Object, Object> parameters )
    {
        int id = Utils.getNumber(parameters.get(0));

        MainHandler.getInstance().harvestablesHandler.removeHarvestable(id);
    }

    public void HarvestStart(HashMap<Object, Object> parameters)
    {
        int harvestableId = Utils.getNumber(parameters.get(0));
        float time = Utils.getFloat(parameters.get(5));
    }

    public void HarvestFinished(HashMap<Object, Object> parameters)
    {
        int playerId = Utils.getNumber(parameters.get(0));
        int harvestableId = Utils.getNumber(parameters.get(3));
        int gathered = Utils.getNumber(parameters.get(4));
        int yield = Utils.getNumber(parameters.get(5));
        int premium = Utils.getNumber(parameters.get(6));
        int charges = Utils.getNumber(parameters.get(7));
    }

    public void NewSimpleItem(HashMap<Object, Object> parameters, EventCodes eventCode)
    {
        if(eventCode != EventCodes.NewSimpleItem)
        {
            Log.d("NewSimpleItem","eventCode: " + eventCode.name());
        }

        int objectID = Utils.getNumber(parameters.get(0));
        int itemID = Utils.getNumber(parameters.get(1));
        int amount = Utils.getNumber(parameters.get(2));
        int avgValue = Utils.getNumber(parameters.get(4)) / 10000;
        String nickname = Utils.getString(parameters.get(5));
    }

    public void NewFloatObject(HashMap<Object, Object> parameters)
    {
        int bobberID = Utils.getNumber(parameters.get(0));
        float[] bobberPos =  Utils.getFloats(parameters.get(1));
        float angleFromPlayer = Utils.getFloat(parameters.get(2));
        int playerId = Utils.getNumber(parameters.get(3));
        int fishingState = Utils.getNumber(parameters.get(4));

        String readableState;

        switch (fishingState)
        {
            case 1:
                readableState = "Floating";
                break;
            case 2:
                readableState = "Bitten";
                break;
            case 3:
                readableState = "Minigame";
                break;
            case 4:
                readableState = "Catched";
                break;
            case 5:
                readableState = "Lost";
                break;
            default:
                readableState = "";
                break;
        }
    }
}