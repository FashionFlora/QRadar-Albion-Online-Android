package com.minhui.vpn.PhotonPackageParser.classes;

import android.util.Log;

import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Events.NewChestEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewHarvestableEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewMobEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewPlayerEvent;
import com.minhui.vpn.PhotonPackageParser.Utils;

import org.greenrobot.eventbus.EventBus;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;

public class PhotonPacketTemp {


    NewPlayerEvent newPlayerEvent = new NewPlayerEvent();
    NewMobEvent newMobEvent = new NewMobEvent();
    NewHarvestableEvent newHarvestableEvent = new NewHarvestableEvent();
    NewChestEvent newChestEvent = new NewChestEvent();
    float [] position = new float[2];



    public  void receive(ByteBuffer p)
    {

        try {


            int peerId = Short.toUnsignedInt(p.getShort());
            int crcEnabled = Byte.toUnsignedInt(p.get());
            int commandCount = Byte.toUnsignedInt(p.get());
            int timestamp = p.getInt();
            int challenge = p.getInt();

            int commandHeaderLength = 12;
            int signifierByteLength = 1;

            for (int commandIdx = 0; commandIdx < commandCount; commandIdx++) {
                int commandType = Byte.toUnsignedInt(p.get());
                int channelId = Byte.toUnsignedInt(p.get());
                int commandFlags = Byte.toUnsignedInt(p.get());
                int unkBytes = Byte.toUnsignedInt(p.get());
                int commandLength = p.getInt();
                int sequenceNumber = p.getInt();

                switch (commandType) {
                    case 4: // Disconnect
                        break;
                    case 7: // Send unreliable
                        p.position(p.position() + 4);
                        commandLength -= 4;
                        // fall through
                    case 6: // Send reliable
                        p.position(p.position() + signifierByteLength);
                        int messageType = Byte.toUnsignedInt(p.get());

                        int operationLength = commandLength - commandHeaderLength - 2;
                        byte[] tempBytes = new byte[operationLength];
                        p.get(tempBytes);
                        ByteBuffer payload = ByteBuffer.wrap(tempBytes);
                        switch (messageType) {
                            case 2:
                                byte operationCode = payload.get();
                                onRequest(operationCode , Protocol16Deserializer.deserializeOperationRequest(payload));
                                break;
                            case 3:

                                byte operationResponse = payload.get();
                                 Protocol16Deserializer.deserializeOperationResponse(payload);
                                //   this.parent.emit('response', this.data);
                                break;
                            case 4:

                                byte eventCode =  payload.get();

                                onEvent(eventCode , Protocol16Deserializer.deserializeEventData(payload));
                                //   this.parent.emit('event', this.data);
                                break;
                        }
                        break;

                    default:
                        p.position(p.position() + commandLength - commandHeaderLength);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("PackeHandler Exception: " + e.toString());
        }

    }




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
            case 200:

                newPlayerEvent.updateMounted(Parameters);
                break;

            case 43:
                newMobEvent.updateEnchant(Parameters);

                break;

            case 117:

                newMobEvent.addNewMob(Parameters);

                break;



            case 35:

                newHarvestableEvent.addNewHarvestableSimple(Parameters);


                break;


            case 36:

                newHarvestableEvent.addNewHarvestable(Parameters);

                break;


            case 57:
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
