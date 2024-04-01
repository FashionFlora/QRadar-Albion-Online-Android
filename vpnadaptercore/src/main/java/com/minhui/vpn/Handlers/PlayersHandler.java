package com.minhui.vpn.Handlers;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.minhui.vpn.Handlers.HandlerItem.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Collectors;

public class PlayersHandler
{
    ArrayList<Player> playersInRange = new ArrayList<>();
    private Player localPlayer;

    public PlayersHandler()
    {
        playersInRange = new ArrayList<>();
        localPlayer = new Player();
    }

    public  ArrayList<Player> getPlayersInRange()
    {
        SharedLocks.playerHandlerLock.readLock().lock();

        try
        {
            return new ArrayList<>(playersInRange);
        }
        finally
        {
            SharedLocks.playerHandlerLock.readLock().unlock();
        }
    }

    public void addPlayer(float posX, float posY, int id, String nickname, String guildName, float currentHealth, float maxHealth, float currentEnergy, float maxEnergy, short[] items)
    {
        SharedLocks.playerHandlerLock.writeLock().lock();

        try
        {
            Player player = new Player(id, posX, posY, nickname, guildName, currentHealth, maxHealth, currentEnergy, maxEnergy, items);
            playersInRange.add(player);
        }
        finally
        {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }
    }
    public void updatePlayerMounted(int id, boolean mounted)
    {
        SharedLocks.playerHandlerLock.writeLock().lock();

        try
        {
            for (Player player : playersInRange)
            {
                if (player.getId() == id)
                {
                    player.setMounted(mounted);
                    break;
                }
            }
        }
        finally
        {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }
    }

    public  void removePlayer(int id)
    {
        SharedLocks.playerHandlerLock.writeLock().lock();

        try
        {
            playersInRange.removeIf(player -> player.getId() == id);
        }
        finally
        {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }
    }

    public void updateLocalPlayerPosition(float posX, float posY, float angle, float targetposX, float targetposY, float speed)
    {
        synchronized (SharedLocks.localPlayerLock)
        {
            localPlayer.setPosX(posX);
            localPlayer.setPosY(posY);

            localPlayer.setAngle(angle);

            localPlayer.setTargetPosX(targetposX);
            localPlayer.setTargetPosY(targetposY);

            localPlayer.setSpeed(speed);
        }
    }

    public void updateLocalPlayer(int characterId, int[] markId, String nickname, String guildName, String cluster, float posX, float posY,
                                  float angle, float currentHealth, float maxHealth, float currentEnergy, float maxEnergy, int faction)
    {
        synchronized (SharedLocks.localPlayerLock)
        {
            localPlayer.Update(characterId, markId, nickname, guildName, cluster, posX, posY, angle,
                    currentHealth, maxHealth, currentEnergy, maxEnergy, faction);
        }
    }

    public String localPlayerGuildName()
    {
        synchronized (SharedLocks.localPlayerLock)
        {
            return localPlayer.getGuild();
        }
    }

    public String localPlayerAlliance()
    {
        synchronized (SharedLocks.localPlayerLock)
        {
            return localPlayer.getAlliance();
        }
    }

    public float localPlayerFaction()
    {
        synchronized (SharedLocks.localPlayerLock)
        {
            return localPlayer.getFaction();
        }
    }


    public  float localPlayerPosX()
    {
        synchronized (SharedLocks.localPlayerLock)
        {
            return localPlayer.getPosX();
        }
    }

    public   float localPlayerPosY()
    {
        synchronized (SharedLocks.localPlayerLock)
        {
            return localPlayer.getPosY();
        }
    }

    public  void updatePlayerPosition(int id, float posX, float posY)
    {
        SharedLocks.playerHandlerLock.writeLock().lock();

        try
        {
            for (Player player : playersInRange)
            {
                if (player.getId() == id)
                {
                    player.setPosX(posX);
                    player.setPosY(posY);
                }
            }
        }
        finally
        {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }
    }

    public void updatePlayerHealth(int id, float currentHealth)
    {
        for (Player player : playersInRange)
        {
            if (player.getId() == id)
            {
                player.setCurrentHealth(currentHealth);
                break;
            }
        }
    }


    public void clear()
    {
        SharedLocks.playerHandlerLock.writeLock().lock();

        try
        {
            playersInRange.clear();
        }
        finally
        {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }
    }
}

