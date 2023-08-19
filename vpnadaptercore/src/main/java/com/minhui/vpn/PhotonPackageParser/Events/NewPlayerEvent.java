package com.minhui.vpn.PhotonPackageParser.Events;

import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;

import java.util.HashMap;

public class NewPlayerEvent {



    public  NewPlayerEvent()
    {

    }

    public void removePlayer(HashMap<Object, Object> Parameters)
    {
        MainHandler.getInstance().playersHandler.removePlayer(Utils.getNumber(Parameters.get(0)));
    }

    public void addNewPlayer(HashMap<Object, Object> Parameters)
    {
        int id = Utils.getNumber(Parameters.get(0));
        String name = String.valueOf(Parameters.get(1));

        float[] floatArray = Utils.getFloats(Parameters.get(13));
        String guildName =String.valueOf(Parameters.get(8));



        float currentHealth = 0;
        float initialHealth = (float)Parameters.get(20);
        try
        {
            currentHealth = (float)Parameters.get(19);
        }
        catch(Exception e)
        {
            currentHealth = initialHealth;
        }

        MainHandler.getInstance().playersHandler.addPlayer( floatArray[0], floatArray[1], id, name , guildName, currentHealth ,initialHealth,null );



    }

    public void updatePlayerPosition(int id, float posX , float posY)
    {

        MainHandler.getInstance().playersHandler.updatePlayerPosition(id, posX, posY);

    }

    public void updateMounted(HashMap<Object, Object> parameters) {

        int id = Utils.getNumber(parameters.get(0));

        boolean  mounted = false;

        String ten;


        try
        {
            ten = String.valueOf(parameters.get(8));
        }
        catch(Exception e)
        {
            ten = "2";
        }
        try
        {

            mounted = (boolean) parameters.get(11);
        }
        catch(Exception e)
        {
            mounted = false;
        }


        if(mounted)
        {

            MainHandler.getInstance().playersHandler.updatePlayerMounted(id, true);


        }
        else if (ten.equals("-1"))
        {
            MainHandler.getInstance().playersHandler.updatePlayerMounted(id, true);



        }
        else
        {
            MainHandler.getInstance().playersHandler.updatePlayerMounted(id, false);


        }


    }
}
