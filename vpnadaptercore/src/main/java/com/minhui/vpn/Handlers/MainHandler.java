package com.minhui.vpn.Handlers;

public class MainHandler {

    private static MainHandler instance;
    private MainHandler() {
    }


    public  PlayersHandler playersHandler = new PlayersHandler();
    public  MobsHandler mobsHandler = new MobsHandler();
    public  HarvestablesHandler harvestablesHandler = new HarvestablesHandler();
    public  ChestHandler chestHandler = new ChestHandler();
    public static MainHandler getInstance() {
        if (instance == null) {
            synchronized (MainHandler.class) {
                if (instance == null) {
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
    }
}
