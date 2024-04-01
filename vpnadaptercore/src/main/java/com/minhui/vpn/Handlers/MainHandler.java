package com.minhui.vpn.Handlers;

import com.minhui.vpn.Handlers.HandlerItem.FishingZone;

public class MainHandler
{

    private static MainHandler instance;
    private MainHandler()
    {
    }

    public  PlayersHandler playersHandler = new PlayersHandler();
    public  MobsHandler mobsHandler = new MobsHandler();
    public  HarvestablesHandler harvestablesHandler = new HarvestablesHandler();
    public  ChestHandler chestHandler = new ChestHandler();
    public FishingZoneHandler fishingZoneHandler = new FishingZoneHandler();
    public static MainHandler getInstance()
    {
        if (instance == null)
        {
            synchronized (MainHandler.class)
            {
                if (instance == null)
                {
                    instance = new MainHandler();
                }
            }
        }
        return instance;
    }

    public void clearAll() {

        playersHandler.clear();
        mobsHandler.clear();
        harvestablesHandler.clear();
        chestHandler.clear();
        fishingZoneHandler.clear();
    }
}
