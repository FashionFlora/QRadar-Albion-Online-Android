package com.minhui.vpn.Handlers;

import com.minhui.vpn.Handlers.HandlerItem.Harvestable;

import java.util.ArrayList;
import java.util.List;

public class HarvestablesHandler {
    private ArrayList<Harvestable> harvestableList;

    public HarvestablesHandler() {
        harvestableList = new ArrayList<>();
    }

    public void addHarvestable(int id, int type, int tier, float posX, float posY, int charges, int size) {

        SharedLocks.harvestablesHandlerLock.writeLock().lock();

        try {
            Harvestable h = new Harvestable(id, type, tier, posX, posY, charges, size);
            if (!harvestableList.contains(h)) {
                harvestableList.add(h);
                // System.out.println("New Harvestable: " + h.toString());
            } else {
                h.setCharges(charges);
            }
        }
        finally {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }
    }


    public void removeNotInRange(float lpX , float lpY)
    {

        SharedLocks.harvestablesHandlerLock.writeLock().lock();
        try {
            harvestableList.removeIf(x -> calculateDistance(lpX,lpY, x.getPosX(), x.getPosY())>80);
        }finally {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }


    }

    public double calculateDistance(double lpX, double lpY, double posX, double posY) {
        double deltaX = lpX - posX;
        double deltaY = lpY - posY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }



    public void removeHarvestable(int id) {

        SharedLocks.harvestablesHandlerLock.writeLock().lock();
        try
        {
            harvestableList.removeIf(x -> x.getId() == id);
        }  finally {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }


    }

    public ArrayList<Harvestable> getHarvestableList() {

        SharedLocks.harvestablesHandlerLock.readLock().lock();
        try
        {
            return  new ArrayList<>(harvestableList);
        }finally {
            SharedLocks.harvestablesHandlerLock.readLock().unlock();
        }

    }

    public void updateHarvestable(int harvestableId, int count) {

        SharedLocks.harvestablesHandlerLock.writeLock().lock();
        try {
            for (Harvestable h : harvestableList) {
                if (h.getId() == harvestableId) {
                    int size = h.getSize() - count;
                    if (size <= 0) {
                        removeHarvestable(harvestableId);
                    } else {
                        h.setSize(size);
                    }
                    break;
                }
            }
        }
        finally {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }
    }

    public void clear() {

        SharedLocks.harvestablesHandlerLock.writeLock().lock();
        try
        {
            harvestableList.clear();
        }  finally {
            SharedLocks.harvestablesHandlerLock.writeLock().unlock();
        }
    }
}