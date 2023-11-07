package com.minhui.vpn.PhotonPackageParser.classes;

import android.os.Build;
import android.util.Log;



import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Events.NewChestEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewHarvestableEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewMobEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewPlayerEvent;
import com.minhui.vpn.PhotonPackageParser.Utils;

import org.greenrobot.eventbus.EventBus;

import java.io.Console;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class PhotonPacketParser {
    public void handle(ByteBuffer buff) {
        new PhotonPacket(this, buff);
    }

    NewPlayerEvent newPlayerEvent = new NewPlayerEvent();
    NewMobEvent newMobEvent = new NewMobEvent();
    NewHarvestableEvent newHarvestableEvent = new NewHarvestableEvent();
    NewChestEvent newChestEvent = new NewChestEvent();
    float [] position = new float[2];



    protected void  onEvent(byte code , HashMap<Object, Object> Parameters)
    {

        if(code==3)
        {
            short intCode  =3;
            Parameters.put(252 , intCode);
            int id = Integer.parseInt(Parameters.get(0).toString());
            byte[] bytes = (byte[]) Parameters.get(1);
            position[0]= Float.intBitsToFloat(Utils.bytesToInt(bytes, 9));
            position[1] = Float.intBitsToFloat(Utils.bytesToInt(bytes, 13));
            newPlayerEvent.updatePlayerPosition(id,position[0], position[1]);
            newMobEvent.updateMobPosition(id,position[0],position[1]);


        }
        int eventCode =Utils.getNumber(Parameters.get(252));






        switch (eventCode)
        {
            case 1:
                newPlayerEvent.removePlayer(Parameters);
                newMobEvent.removeMob(Parameters);
                newHarvestableEvent.removeHarvestable(Parameters);
                newChestEvent.removeChest(Parameters);
                break;
            case  27:
                newPlayerEvent.addNewPlayer(Parameters);
                break;
            case 201:

                newPlayerEvent.updateMounted(Parameters);
                break;

            case 44:
                newMobEvent.updateEnchant(Parameters);

                break;

            case 118:

                newMobEvent.addNewMob(Parameters);

                break;



            case 36:

               newHarvestableEvent.addNewHarvestableSimple(Parameters);


                break;


            case 37:

               newHarvestableEvent.addNewHarvestable(Parameters);

                break;


            case 58:
                newHarvestableEvent.update(Parameters);

                break;


            case 378:

                newChestEvent.addNewChest(Parameters);

                break;





        }






        EventBus.getDefault().post("refresh");
    }

    protected void  onRequest(byte code , HashMap<Object, Object> Parameters)
    {




        short operationCode = (short) Parameters.get(253);

        Log.d("operation",""+operationCode);

        switch (operationCode)
        {
            case 21:


                Object[] objectArray = (Object[]) Parameters.get(1);
                float[] floatArray = new float[objectArray.length];

                for (int i = 0; i < objectArray.length; i++) {
                    if (objectArray[i] instanceof Float) {
                        floatArray[i] = (float) objectArray[i];

                    } else {
                        // Handle the case where the element is not a Float
                        // You can throw an exception, provide a default value, or take any other appropriate action.
                        // For example:
                        // floatArray[i] = defaultValue;
                    }
                }
                MainHandler.getInstance().playersHandler.updateLocalPlayerPosition(floatArray[0], floatArray[1]);
                MainHandler.getInstance().harvestablesHandler.removeNotInRange(floatArray[0],floatArray[1]);
                break;

            case 35:

                MainHandler.getInstance().clearAll();

                break;


        }






        EventBus.getDefault().post("refresh");





    }


    protected  void  onResponse(byte code , HashMap<Object, Object> Parameters)
    {
        Log.d("onWhatCode", "onResponse");


    }




}
