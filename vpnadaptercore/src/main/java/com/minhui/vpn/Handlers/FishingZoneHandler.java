package com.minhui.vpn.Handlers;

import android.util.Log;

import com.minhui.vpn.Handlers.HandlerItem.FishingZone;
import com.minhui.vpn.PhotonPackageParser.Utils;

import java.util.ArrayList;

public class FishingZoneHandler
{
    private ArrayList<FishingZone> FishingZoneList;

    public FishingZoneHandler()
    {
        FishingZoneList = new ArrayList<>();
    }

    public ArrayList<FishingZone> getFishingZoneList()
    {
        SharedLocks.fishingZonesHandlerLock.readLock().lock();

        try
        {
            return  new ArrayList<>(FishingZoneList);
        }
        finally
        {
            SharedLocks.fishingZonesHandlerLock.readLock().unlock();
        }
    }

    public void addFishingZone(int id, float posX, float posY, int charges, int fished, String zoneType)
    {
        SharedLocks.fishingZonesHandlerLock.writeLock().lock();

        try
        {
            FishingZone f = new FishingZone(id, posX, posY, charges, fished, zoneType);
            FishingZoneList.add(f);

            //Log.d("NewFishingZoneObject","id: " + id + " Pos: " + posX + "," + posY);
        }
        finally
        {
            SharedLocks.fishingZonesHandlerLock.writeLock().unlock();
        }
    }

    public void updateFishingZone(int FishingZoneId, int charges, int Fished)
    {
        SharedLocks.fishingZonesHandlerLock.writeLock().lock();

        try
        {
            for (FishingZone h : FishingZoneList)
            {
                if (h.getId() == FishingZoneId)
                {
                    if (charges <= 0)
                    {
                        h.setCharges(0);
                        h.setFished(Fished);
                    }
                    else
                    {
                        h.setCharges(charges);
                        h.setFished(Fished);
                    }
                    break;
                }
            }
        }
        finally
        {
            SharedLocks.fishingZonesHandlerLock.writeLock().unlock();
        }
    }

    public void removeFishingZone(int id)
    {
        SharedLocks.fishingZonesHandlerLock.writeLock().lock();

        try
        {
            FishingZoneList.removeIf(x -> x.getId() == id);
        }
        finally
        {
            SharedLocks.fishingZonesHandlerLock.writeLock().unlock();
        }
    }

    public void removeNotInRange(float lpX , float lpY)
    {
        SharedLocks.fishingZonesHandlerLock.writeLock().lock();

        try
        {
            FishingZoneList.removeIf(x -> Utils.calculateDistance(lpX, lpY, x.getPosX(), x.getPosY()) > Utils.MaxDistance);
        }
        finally
        {
            SharedLocks.fishingZonesHandlerLock.writeLock().unlock();
        }
    }

    public void clear()
    {
        SharedLocks.fishingZonesHandlerLock.writeLock().lock();

        try
        {
            FishingZoneList.clear();
        }
        finally
        {
            SharedLocks.fishingZonesHandlerLock.writeLock().unlock();
        }
    }
}
