package com.minhui.vpn.Handlers;

import com.minhui.vpn.Handlers.HandlerItem.Chest;
import com.minhui.vpn.Handlers.HandlerItem.Player;

import java.util.ArrayList;

public class ChestHandler {
    private ArrayList<Chest> chestsList;

    public ChestHandler() {
        chestsList = new ArrayList<>();
    }

    public void addChest(int id, float posX, float posY, String name)
    {
        SharedLocks.chestsHandlerLock.writeLock().lock();

        try
        {
            Chest chest = new Chest(id, posX, posY, name);

            if (!chestsList.contains(chest))
            {
                chestsList.add(chest);
            }
        }
        finally
        {
            SharedLocks.chestsHandlerLock.writeLock().unlock();
        }
    }

    public void removeChest(int id)
    {
        SharedLocks.chestsHandlerLock.writeLock().lock();

        try
        {
            chestsList.removeIf(chest -> chest.getId() == id);
        }
        finally
        {
            SharedLocks.chestsHandlerLock.writeLock().unlock();
        }
    }

    public  ArrayList<Chest> getChests()
    {
        SharedLocks.chestsHandlerLock.readLock().lock();

        try
        {
            return new ArrayList<Chest>(chestsList);
        }
        finally
        {
            SharedLocks.chestsHandlerLock.readLock().unlock();
        }
    }

    public void clear() {

        SharedLocks.chestsHandlerLock.writeLock().lock();

        try
        {
            chestsList.clear();
        }
        finally
        {
            SharedLocks.chestsHandlerLock.writeLock().unlock();
        }
    }
}