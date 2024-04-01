package com.minhui.vpn.PhotonPackageParser.classes;

import android.util.Log;

import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Events.NewAuctionEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewChestEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewFishingZoneEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewHarvestableEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewMobEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewOthersEvent;
import com.minhui.vpn.PhotonPackageParser.Events.NewPlayerEvent;
import com.minhui.vpn.PhotonPackageParser.Utils;
import com.minhui.vpn.PhotonPackageParser.enumerations.EventCodes;
import com.minhui.vpn.PhotonPackageParser.enumerations.OperationCodes;

import org.greenrobot.eventbus.EventBus;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class PhotonPacketParser
{
    NewPlayerEvent newPlayerEvent = new NewPlayerEvent();
    NewMobEvent newMobEvent = new NewMobEvent();
    NewHarvestableEvent newHarvestableEvent = new NewHarvestableEvent();
    NewChestEvent newChestEvent = new NewChestEvent();
    NewFishingZoneEvent newFishingZoneEvent = new NewFishingZoneEvent();
    NewOthersEvent newOthersEvent = new NewOthersEvent();
    NewAuctionEvent newAuctionEvent = new NewAuctionEvent();

    float [] position = new float[2];

    public  void receive(ByteBuffer p)
    {
        try
        {
            int peerId = Short.toUnsignedInt(p.getShort());
            int crcEnabled = Byte.toUnsignedInt(p.get());
            int commandCount = Byte.toUnsignedInt(p.get());
            int timestamp = p.getInt();
            int challenge = p.getInt();

            int commandHeaderLength = 12;
            int signifierByteLength = 1;

            for (int commandIdx = 0; commandIdx < commandCount; commandIdx++)
            {
                int commandType = Byte.toUnsignedInt(p.get());
                int channelId = Byte.toUnsignedInt(p.get());
                int commandFlags = Byte.toUnsignedInt(p.get());
                int unkBytes = Byte.toUnsignedInt(p.get());
                int commandLength = p.getInt();
                int sequenceNumber = p.getInt();

                switch (commandType)
                {
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

                        switch (messageType)
                        {
                            case 2:
                                byte operationCode = payload.get();
                                onRequest(operationCode , Protocol16Deserializer.deserializeOperationRequest(payload));
                                break;

                            case 3:
                                byte operationResponse = payload.get();
                                onResponse(operationResponse, Protocol16Deserializer.deserializeOperationResponse(payload));
                                break;

                            case 4:
                                byte eventCode =  payload.get();
                                onEvent(eventCode , Protocol16Deserializer.deserializeEventData(payload));
                                break;

                            default:
                                byte eventCode2 =  payload.get();
                                System.out.println("eventCode2: " + eventCode2);

                                //onOther(eventCode2 , Protocol16Deserializer.deserialize(payload, 1));


                                //onResponse(eventCode2, Protocol16Deserializer.deserializeOperationResponse(payload));

                                break;
                        }
                        break;

                    default:
                        p.position(p.position() + commandLength - commandHeaderLength);
                        break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("PackeHandler Exception: " + e.toString());
        }
    }

    protected void onEvent(byte code , HashMap<Object, Object> Parameters)
    {
        int Code = -1;

        try
        {
            if(code == 3)
            {
                Code = 3;

                Parameters.put(252 , code);

                int id = Utils.getNumber(Parameters.get(0));
                byte[] bytes = Utils.getByteArray(Parameters.get(1));

                position[0]= Float.intBitsToFloat(Utils.bytesToInt(bytes, 9));
                position[1] = Float.intBitsToFloat(Utils.bytesToInt(bytes, 13));

                newPlayerEvent.updatePlayerPosition(id, position[0], position[1]);
                newMobEvent.updateMobPosition(id, position[0], position[1]);
            }
            else
            {
                Code = Utils.getNumber(Parameters.get(252));

                EventCodes eventCode = EventCodes.values()[Code];

                switch (eventCode)
                {
                    case Leave:
                        newPlayerEvent.removePlayer(Parameters);
                        newMobEvent.removeMob(Parameters);
                        newHarvestableEvent.removeHarvestable(Parameters);
                        newFishingZoneEvent.removeFishingZoneObject(Parameters);
                        newChestEvent.removeChest(Parameters);
                        break;

                    case NewCharacter:
                        newPlayerEvent.NewCharacter(Parameters);
                        break;

                    case Mounted:
                        newPlayerEvent.Mounted(Parameters);
                        break;

                    case NewMountObject:
                        newPlayerEvent.NewMount(Parameters);
                        break;

                    case InCombatStateUpdate:
                        newPlayerEvent.InCombatStateUpdate(Parameters);
                        break;

                    case HealthUpdate:
                        newPlayerEvent.HealthUpdate(Parameters);
                        break;

                    case Attack:
                        newPlayerEvent.Attack(Parameters);
                        break;

                    case NewSimpleItem:
                    case NewEquipmentItem:
                    case NewFurnitureItem:
                    case NewJournalItem:
                    case NewLaborerItem:
                        newHarvestableEvent.NewSimpleItem(Parameters, eventCode);
                        break;

                    case UpdateMoney:
                        newPlayerEvent.UpdateMoney(Parameters);
                        break;

                    case MobChangeState:
                        newMobEvent.MobChangeState(Parameters);
                        break;

                    case NewMob:
                        newMobEvent.NewMob(Parameters);
                        break;

                    case NewSimpleHarvestableObjectList:
                        newHarvestableEvent.NewSimpleHarvestableObjectList(Parameters);
                        break;

                    case NewHarvestableObject:
                        newHarvestableEvent.NewHarvestableObject(Parameters);
                        break;

                    case HarvestableChangeState:
                        newHarvestableEvent.HarvestableChangeState(Parameters);
                        break;

                    case HarvestStart:
                        newHarvestableEvent.HarvestStart(Parameters);
                        break;

                    case HarvestFinished:
                        newHarvestableEvent.HarvestFinished(Parameters);
                        break;

                    case NewFloatObject:
                        newHarvestableEvent.NewFloatObject(Parameters);
                        break;

                    case NewTreasureChest:
                        newChestEvent.NewTreasureChest(Parameters);
                        break;

                    case NewFishingZoneObject:
                        newFishingZoneEvent.NewFishingZoneObject(Parameters);
                        break;

                    case FishingStart:
                        newFishingZoneEvent.FishingStart(Parameters);
                        break;

                    case FishingFinished:
                        newFishingZoneEvent.FishingFinished(Parameters);
                        break;

                    case FishingCatch:
                        newFishingZoneEvent.FishingCatch(Parameters);
                        break;

                    case FishingCast:
                        newFishingZoneEvent.FishingCast(Parameters);
                        break;

                    case FishingMiniGame:
                        newFishingZoneEvent.FishingMiniGame(Parameters);
                        break;

                    case InventoryPutItem:
                        newPlayerEvent.InventoryPutItem(Parameters);
                        break;

                    case NewBuilding:
                        newPlayerEvent.NewBuilding(Parameters);
                        break;

                    case NewLoot:
                        newOthersEvent.NewLoot(Parameters);
                        break;

                    case NewExit:
                        newOthersEvent.NewExit(Parameters);
                        break;

                    case NewRandomDungeonExit:
                        newOthersEvent.NewRandomDungeonExit(Parameters);
                        break;

                    default:
                        break;

                }
            }
        }
        catch (Exception ex )
        {
            Log.d("onEvent","Code: " + Code + " Error:" + ex.toString());
        }

        EventBus.getDefault().post("refresh");
    }

    protected void onRequest(byte code , HashMap<Object, Object> Parameters)
    {
        short Code = (short)Parameters.get(253);

        try
        {
            OperationCodes operationCode = OperationCodes.values()[Code];

            switch (operationCode)
            {
                case Move:
                    float[] pos = Utils.getFloats(Parameters.get(1));
                    float angle = Utils.getFloat(Parameters.get(2));
                    float[] targetpos = Utils.getFloats(Parameters.get(3));
                    float speed = Utils.getFloat(Parameters.get(4));

                    MainHandler.getInstance().playersHandler.updateLocalPlayerPosition(pos[0], pos[1], angle, targetpos[0], targetpos[1], speed);
                    MainHandler.getInstance().harvestablesHandler.removeNotInRange(pos[0],pos[1]);
                    MainHandler.getInstance().fishingZoneHandler.removeNotInRange(pos[0],pos[1]);
                    break;

                case ChangeCluster :
                    MainHandler.getInstance().clearAll();
                    break;

                case AuctionGetOffers:
                    newAuctionEvent.AuctionGetOffersRequest(Parameters);
                    break;
            }
        }
        catch (Exception ex)
        {
            Log.d("onRequest","Code: " + Code);
        }

        EventBus.getDefault().post("refresh");
    }

    protected void onResponse(byte code , HashMap<Object, Object> Parameters)
    {
        short Code = (short)Parameters.get(253);

        try
        {
            OperationCodes operationCode = OperationCodes.values()[Code];

            switch (operationCode)
            {
                case Join:
                    newPlayerEvent.updateLocalPlayer(Parameters);
                    break;

                case AuctionGetOffers:
                    newAuctionEvent.AuctionGetOffersResponse(Parameters);
                    break;

                default:
                    //Log.d("onResponse operationCode","Code: " + operationCode);

                    break;
            }
        }
        catch (Exception ex)
        {
            Log.d("onResponse","Code: " + Code);
        }

        EventBus.getDefault().post("refresh");
    }

    protected void onOther(byte code , HashMap<Object, Object> Parameters)
    {
        short Code = (short)Parameters.get(253);

    }
}
