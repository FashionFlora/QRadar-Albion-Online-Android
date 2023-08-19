package com.minhui.vpn.Handlers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.minhui.vpn.Handlers.HandlerItem.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Collectors;

public class PlayersHandler {
    ArrayList<Player> playersInRange = new ArrayList<>();
    private Player localPlayer;
    private boolean invalidate;



    public PlayersHandler() {
        playersInRange = new ArrayList<>();
        localPlayer = new Player();
    }

    public  ArrayList<Player> getPlayersInRange()
    {

        SharedLocks.playerHandlerLock.readLock().lock();
        try {
            return new ArrayList<>(playersInRange);
        } finally {
            SharedLocks.playerHandlerLock.readLock().unlock();
        }

    }

    public  void  addPlayer(float posX, float posY, int id, String nickname, String guildName, float currentHealth, float initialHealth, short[] items) {

        SharedLocks.playerHandlerLock.writeLock().lock();
        try {
            Player player = new Player(posX, posY, id, nickname, guildName ,  currentHealth, initialHealth, items);
            playersInRange.add(player);
        }
        finally {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }
    }

    public void updateLocalPlayerNextPosition(float posX, float posY) {
        // TODO: Implement update local player next position
        throw new UnsupportedOperationException();
    }

    public void updatePlayerMounted(int id, boolean mounted) {

        SharedLocks.playerHandlerLock.writeLock().lock();
        try {
            for (Player player : playersInRange) {
                if (player.getId() == id) {
                    player.setMounted(mounted);
                    break;
                }
            }
        } finally {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }


    }






    public  void removePlayer(int id) {


        SharedLocks.playerHandlerLock.writeLock().lock();
        try {

            playersInRange.removeIf(player -> player.getId() == id);
        } finally {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }



    }


    public    void  updateLocalPlayerPosition(float posX, float posY) {

        synchronized (SharedLocks.localPlayerLock) {
            localPlayer.setPosX(posX);
            localPlayer.setPosY(posY);
        }

    }




    public  float localPlayerPosX() {

        synchronized (SharedLocks.localPlayerLock) {
            return localPlayer.getPosX();
        }

    }

    public   float localPlayerPosY() {

        synchronized (SharedLocks.localPlayerLock) {
            return localPlayer.getPosY();
        }
    }

    public  void updatePlayerPosition(int id, float posX, float posY) {

        SharedLocks.playerHandlerLock.writeLock().lock();
        try {
            for (Player player : playersInRange) {
                if (player.getId() == id) {
                    player.setPosX(posX);
                    player.setPosY(posY);
                }
            }
        } finally {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }
    }

    public void updatePlayerHealth(int id, float currentHealth) {
        for (Player player : playersInRange) {
            if (player.getId() == id) {
                player.setCurrentHealth(currentHealth);
                break;
            }
        }
    }

    public void updatePlayerInitialHealth(int id, float currentHealth, float initialHealth) {
        for (Player player : playersInRange) {
            if (player.getId() == id) {
                player.setCurrentHealth(currentHealth);
                player.setInitialHealth(initialHealth);
                break;
            }
        }
    }


    public void clear() {

        SharedLocks.playerHandlerLock.writeLock().lock();
        try {

            playersInRange.clear();

        } finally {
            SharedLocks.playerHandlerLock.writeLock().unlock();
        }
    }
}

