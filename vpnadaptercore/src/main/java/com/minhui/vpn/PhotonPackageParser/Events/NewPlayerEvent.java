package com.minhui.vpn.PhotonPackageParser.Events;

import android.util.Log;

import com.google.gson.Gson;
import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;

import java.util.HashMap;

public class NewPlayerEvent
{
    final Gson gson = new Gson();

    public  NewPlayerEvent()
    {

    }

    public void removePlayer(HashMap<Object, Object> Parameters)
    {
        MainHandler.getInstance().playersHandler.removePlayer(Utils.getNumber(Parameters.get(0)));
    }

    public void NewCharacter(HashMap<Object, Object> Parameters)
    {
        int id = Utils.getNumber(Parameters.get(0));
        String name = String.valueOf(Parameters.get(1));
        String guildName =String.valueOf(Parameters.get(8));

        float[] floatArray = Utils.getFloats(Parameters.get(14));
        float currentHealth = Utils.getFloat(Parameters.get(20));
        float maxHealth = Utils.getFloat(Parameters.get(21));
        float currentEnergy = Utils.getFloat(Parameters.get(25));
        float maxEnergy = Utils.getFloat(Parameters.get(26));
        int[] items = Utils.getKnownArray(Parameters.get(38));
        int[] skills = Utils.getKnownArray(Parameters.get(41));
        int faction = Utils.getNumber(Parameters.get(51));

        String JsonData = gson.toJson(Parameters);
        Log.d("onNewCharacter", JsonData);

        MainHandler.getInstance().playersHandler.addPlayer(floatArray[0], floatArray[1], id, name , guildName, currentHealth, maxHealth, currentEnergy, maxEnergy, null );
    }

    public void updatePlayerPosition(int id, float posX , float posY)
    {
        MainHandler.getInstance().playersHandler.updatePlayerPosition(id, posX, posY);
    }

    public void updateLocalPlayer(HashMap<Object, Object> parameters)
    {
        int id = Utils.getNumber(parameters.get(0));
        int[] markId = Utils.getKnownArray(parameters.get(1));
        String cName = Utils.getString(parameters.get(2));
        String cluster = Utils.getString(parameters.get(8));
        float[] pos = Utils.getFloats(parameters.get(9));

        float angle = 0;

        if (parameters.containsKey(10))
        {
            angle = Utils.getFloat(parameters.get(10));
        }
        else
        {
            angle = Utils.getFloat(parameters.get(14));
        }

        float currentHealth = Utils.getFloat(parameters.get(11));
        float maxHealth = Utils.getFloat(parameters.get(12));
        float currentEnergy = Utils.getFloat(parameters.get(16));
        float maxEnergy = Utils.getFloat(parameters.get(17));

        int faction = Utils.getNumber(parameters.get(43));
        String guildName = Utils.getString(parameters.get(57));
        String title = Utils.getString(parameters.get(77));

        MainHandler.getInstance().playersHandler.updateLocalPlayer(id, markId, cName, guildName, cluster, pos[0], pos[1], angle, currentHealth, maxHealth, currentEnergy, maxEnergy, faction);

        String JsonData = gson.toJson(parameters);
        Log.d("onUpdateLocalPlayer", JsonData);
    }

    public void Mounted(HashMap<Object, Object> parameters)
    {
        int id = Utils.getNumber(parameters.get(0));
        String ten = Utils.getString(parameters.get(8));
        boolean mounted = Utils.getBoolean(parameters.get(11));

        if(mounted)
        {
            MainHandler.getInstance().playersHandler.updatePlayerMounted(id, true);
        }
        else if (ten != null && ten.equals("-1"))
        {
            MainHandler.getInstance().playersHandler.updatePlayerMounted(id, true);
        }
        else
        {
            MainHandler.getInstance().playersHandler.updatePlayerMounted(id, false);
        }
    }

    public void InCombatStateUpdate(HashMap<Object, Object> parameters)
    {
        int playerId = Utils.getNumber(parameters.get(0));
        boolean playerAttacking =  Utils.getBoolean(parameters.get(1));
        boolean enemyAttacking  =  Utils.getBoolean(parameters.get(2));
    }

    public void InventoryPutItem(HashMap<Object, Object> parameters)
    {
        int id = Utils.getNumber(parameters.get(0));
    }

    public void NewMount(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
        float[] pos =  Utils.getFloats(parameters.get(3));
        byte[] items = Utils.getByteArray(parameters.get(5));
    }

    public void UpdateMoney(HashMap<Object, Object> parameters)
    {
        int id =  Utils.getNumber(parameters.get(0));
        String silver =  Utils.getString(parameters.get(1));

        int len = silver.length() - 4;
        long currentSilver = Long.parseLong(silver.substring(0, len));
    }

    public void HealthUpdate(HashMap<Object, Object> parameters)
    {
        int attackerEntityId = Utils.getNumber(parameters.get(0));
        int targetHealthUpdate = Utils.getNumber(parameters.get(2));
        int targetCurrentHealth = Utils.getNumber(parameters.get(3));
        int targetEntityId = Utils.getNumber(parameters.get(6));

    }

    public void Attack(HashMap<Object, Object> parameters)
    {
        int attackerID = Utils.getNumber(parameters.get(0));
        int targetID = Utils.getNumber(parameters.get(2));
    }

    public void NewBuilding(HashMap<Object, Object> parameters)
    {
        int packetID =  Utils.getNumber(parameters.get(0));
        String name =  Utils.getString(parameters.get(3));
        float[] pos =  Utils.getFloats(parameters.get(4));
    }
}
