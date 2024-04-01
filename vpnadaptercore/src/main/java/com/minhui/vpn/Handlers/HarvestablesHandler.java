package com.minhui.vpn.Handlers;

import com.minhui.vpn.Handlers.HandlerItem.Harvestable;
import com.minhui.vpn.Handlers.HandlerItem.Mob;
import com.minhui.vpn.PhotonPackageParser.Utils;

import java.util.ArrayList;
import java.util.List;

public class HarvestablesHandler
{
    private ArrayList<Harvestable> harvestableList;

    public HarvestablesHandler() {
        harvestableList = new ArrayList<>();
    }

    public void addHarvestable(int id, int type, int tier, float posX, float posY, int charges, int enchant)
    {
        SharedLocks.harvestablesHandlerLock.writeLock().lock();

        try
        {
            Harvestable h = new Harvestable(id, type, tier, posX, posY, charges, enchant);
            harvestableList.add(h);
        }
        finally
        {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }
    }

    public void removeNotInRange(float lpX , float lpY)
    {
        SharedLocks.harvestablesHandlerLock.writeLock().lock();

        try
        {
            harvestableList.removeIf(x -> Utils.calculateDistance(lpX, lpY, x.getPosX(), x.getPosY()) > Utils.MaxDistance);
        }
        finally
        {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }
    }

    public void removeHarvestable(int id)
    {
        SharedLocks.harvestablesHandlerLock.writeLock().lock();

        try
        {
            harvestableList.removeIf(x -> x.getId() == id);
        }
        finally
        {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }
    }

    public ArrayList<Harvestable> getHarvestableList()
    {
        SharedLocks.harvestablesHandlerLock.readLock().lock();

        try
        {
            return  new ArrayList<>(harvestableList);
        }
        finally
        {
            SharedLocks.harvestablesHandlerLock.readLock().unlock();
        }
    }

    public void updateHarvestable(int harvestableId, int charges, int enchantment)
    {
        SharedLocks.harvestablesHandlerLock.writeLock().lock();

        try
        {
            for (Harvestable h : harvestableList)
            {
                if (h.getId() == harvestableId)
                {
                    if (charges <= 0)
                    {
                        h.setCharges(0);
                        h.setEnchant(enchantment);
                    }
                    else
                    {
                        h.setCharges(charges);
                        h.setEnchant(enchantment);
                    }
                    break;
                }
            }
        }
        finally
        {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }
    }

    public void clear()
    {
        SharedLocks.harvestablesHandlerLock.writeLock().lock();

        try
        {
            harvestableList.clear();
        }
        finally
        {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }
    }
}