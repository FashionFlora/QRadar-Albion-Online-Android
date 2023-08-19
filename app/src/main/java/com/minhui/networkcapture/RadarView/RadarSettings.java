package com.minhui.networkcapture.RadarView;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.minhui.vpn.Handlers.HandlerItem.HarvestableType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadarSettings {
    private static RadarSettings instance;

    public  ArrayList<HarvestableType> harvestableTypes = new ArrayList<>();

    public boolean playerDot = false;
    public boolean playerNickname = false;
    public boolean playerHealth = false;
    public boolean playerMounted = false;
    public boolean playerGuildName = false;
    public boolean playerDistance = false;
    public boolean playerSound= false;

    public boolean[] harvestingTiers = new boolean[]{false,false,false,false,false,false,false,false};
    public boolean[] harvestingEnchants = new boolean[]{false,false,false,false,false};

    public boolean[] mobEnchants = new boolean[]{false,false,false,false,false};
    public boolean[] mobTiers = new boolean[]{false,false,false,false,false,false,false,false};
    public boolean mobHp = false;
    public int radarFloatingSettingWheelX = -999;
    public int radarFloatingSettingWheelY = -999;

    public int radarFloatingSquareX = -999;
    public int radarFloatingSquareY = -999;
    public boolean mobOther = false;
    public boolean mobSkinnable = false;
    public boolean mobHarvestable = false;
    public boolean mobBoss = false;
    public boolean harvestingFiber;
    public boolean harvestingWood;
    public boolean harvestingHide;
    public boolean harvestingOre;
    public boolean harvestingRock;
    public boolean harvestingSize;

    public HashMap<String, Boolean> chests = new HashMap<>();


    public int pvpCircleBar;
    public int pvpCircleGapBar;

    public int pvpTextSizeBar;
    public int pvpTextGapBar;

    public int harvestingWidthHeightBar;
    public int harvestingIconTextGapBar;
    public int harvestingIconTextSizeBar;



    public int mobTextSizeBar;
    public int mobTextGapBar;
    public int mobRadarSizeBar;
    public int mobIconWidthHeightBar;
    public int chestIconWidthHeightBar;
    public float radarScaleBar = 2.3f;
    public int radarSizeWidthHeightBar;
    public int radarMiddleCircleBar;
    public boolean radarShowTopMost = false;
    public boolean radarShowSquare;
    public boolean radarShowCircle;
    public int radarCircleSquareBorderBar;
    public int radarXBar;
    public int radarYBar;
    public int floatingWidthHeightBar;
    public int settingsHeightBar;
    public int settingsWidthBar;
    public int floatingYBar;
    public int floatingXBar;
    public int settingsTransparencyBar;


    public void init(Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        playerDot = sharedPreferences.getBoolean("playerDot", false);
        playerNickname = sharedPreferences.getBoolean("playerNickname", false);
        playerHealth = sharedPreferences.getBoolean("playerHealth", false);
        playerMounted = sharedPreferences.getBoolean("playerMounted", false);
        playerDistance = sharedPreferences.getBoolean("playerDistance", false);
        playerSound = sharedPreferences.getBoolean("playerSound", false);
        playerGuildName = sharedPreferences.getBoolean("playerGuildName", false);

        harvestingTiers[0] =  sharedPreferences.getBoolean("harvestingTier1", false);
        harvestingTiers[1] =  sharedPreferences.getBoolean("harvestingTier2", false);
        harvestingTiers[2] =  sharedPreferences.getBoolean("harvestingTier3", false);
        harvestingTiers[3] =  sharedPreferences.getBoolean("harvestingTier4", false);
        harvestingTiers[4] =  sharedPreferences.getBoolean("harvestingTier5", false);
        harvestingTiers[5] =  sharedPreferences.getBoolean("harvestingTier6", false);
        harvestingTiers[6] =  sharedPreferences.getBoolean("harvestingTier7", false);
        harvestingTiers[7] =  sharedPreferences.getBoolean("harvestingTier8", false);

        harvestingEnchants[0]  = sharedPreferences.getBoolean("harvestingEnchant0", false);
        harvestingEnchants[1]  = sharedPreferences.getBoolean("harvestingEnchant1", false);
        harvestingEnchants[2]  = sharedPreferences.getBoolean("harvestingEnchant2", false);
        harvestingEnchants[3]  = sharedPreferences.getBoolean("harvestingEnchant3", false);
        harvestingEnchants[4]  = sharedPreferences.getBoolean("harvestingEnchant4", false);


        harvestingFiber = sharedPreferences.getBoolean("harvestingFiber", false);
        harvestingRock = sharedPreferences.getBoolean("harvestingRock", false);
        harvestingOre = sharedPreferences.getBoolean("harvestingOre", false);
        harvestingHide = sharedPreferences.getBoolean("harvestingHide", false);
        harvestingWood = sharedPreferences.getBoolean("harvestingWood", false);

        harvestingSize = sharedPreferences.getBoolean("harvestingSize", false);

        chests.put("standard",sharedPreferences.getBoolean("chestStandard", false));
        chests.put("uncommon",sharedPreferences.getBoolean("chestUncommon", false));
        chests.put("rare",sharedPreferences.getBoolean("chestRare", false));
        chests.put("legendary",sharedPreferences.getBoolean("chestLegendary", false));






        if(harvestingFiber)
        {
            updateHarvestableType(Arrays.asList(
                    HarvestableType.FIBER,
                    HarvestableType.FIBER_CRITTER,
                    HarvestableType.FIBER_GUARDIAN_DEAD,
                    HarvestableType.FIBER_GUARDIAN_RED), true);
        }
        if(harvestingRock)
        {

            updateHarvestableType(Arrays.asList(
                    HarvestableType.ROCK,
                    HarvestableType.ROCK_CRITTER_DEAD,
                    HarvestableType.ROCK_CRITTER_GREEN,
                    HarvestableType.ROCK_CRITTER_RED,
                    HarvestableType.ROCK_GUARDIAN_RED), true);
        }

        if(harvestingOre)
        {

            updateHarvestableType(Arrays.asList(
                    HarvestableType.ORE,
                    HarvestableType.ORE_CRITTER_DEAD,
                    HarvestableType.ORE_CRITTER_GREEN,
                    HarvestableType.ORE_CRITTER_RED,
                    HarvestableType.ORE_GUARDIAN_RED), true);
        }

        if(harvestingWood)
        {

            updateHarvestableType(Arrays.asList(
                    HarvestableType.WOOD,
                    HarvestableType.WOOD_CRITTER_DEAD,
                    HarvestableType.WOOD_CRITTER_GREEN,
                    HarvestableType.WOOD_CRITTER_RED,
                    HarvestableType.WOOD_GIANTTREE,
                    HarvestableType.WOOD_GUARDIAN_RED),true);
        }
        if(harvestingHide)
        {

            updateHarvestableType(Arrays.asList(
                    HarvestableType.HIDE,
                    HarvestableType.HIDE_FOREST,
                    HarvestableType.HIDE_STEPPE,
                    HarvestableType.HIDE_SWAMP,
                    HarvestableType.HIDE_MOUNTAIN,
                    HarvestableType.HIDE_HIGHLAND,
                    HarvestableType.HIDE_CRITTER,
                    HarvestableType.HIDE_GUARDIAN),true);

        }

        mobTiers[0] =  sharedPreferences.getBoolean("mobTier1", false);
        mobTiers[1] =  sharedPreferences.getBoolean("mobTier2", false);
        mobTiers[2] =  sharedPreferences.getBoolean("mobTier3", false);
        mobTiers[3] =  sharedPreferences.getBoolean("mobTier4", false);
        mobTiers[4] =  sharedPreferences.getBoolean("mobTier5", false);
        mobTiers[5] =  sharedPreferences.getBoolean("mobTier6", false);
        mobTiers[6] =  sharedPreferences.getBoolean("mobTier7", false);
        mobTiers[7] =  sharedPreferences.getBoolean("mobTier8", false);

        mobEnchants[0] = sharedPreferences.getBoolean("mobEnchant0", false);
        mobEnchants[1] = sharedPreferences.getBoolean("mobEnchant1", false);
        mobEnchants[2] = sharedPreferences.getBoolean("mobEnchant2", false);
        mobEnchants[3] = sharedPreferences.getBoolean("mobEnchant3", false);
        mobEnchants[4] = sharedPreferences.getBoolean("mobEnchant4", false);




        mobHp =  sharedPreferences.getBoolean("mobHp", false);

        mobHarvestable = sharedPreferences.getBoolean("mobHarvestable", false);
        mobOther = sharedPreferences.getBoolean("mobOther", false);
        mobSkinnable = sharedPreferences.getBoolean("mobSkinnable",false);
        mobBoss = sharedPreferences.getBoolean("mobBoss",false);

        radarFloatingSettingWheelX = sharedPreferences.getInt("radarFloatingSettingWheelX", -999);
        radarFloatingSettingWheelY = sharedPreferences.getInt("radarFloatingSettingWheelY", -999);

        radarFloatingSquareX = sharedPreferences.getInt("radarFloatingSquareX", -999);
        radarFloatingSquareY = sharedPreferences.getInt("radarFloatingSquareY", -999);




        pvpCircleBar = sharedPreferences.getInt("pvpCircleBar", 10);
        pvpCircleGapBar = sharedPreferences.getInt("pvpCircleGapBar", 25);
        pvpTextGapBar =   sharedPreferences.getInt("pvpTextGapBar", 15);
        pvpTextSizeBar =   sharedPreferences.getInt("pvpTextSizeBar", 15);


        harvestingWidthHeightBar = sharedPreferences.getInt("harvestingWidthHeightBar", 50);
        harvestingIconTextGapBar = sharedPreferences.getInt("harvestingIconTextGapBar", 15);
        harvestingIconTextSizeBar =  sharedPreferences.getInt("harvestingIconTextSizeBar", 15);



        mobTextSizeBar=  sharedPreferences.getInt("mobTextSizeBar", 15);
        mobTextGapBar=  sharedPreferences.getInt("mobTextGapBar", 15);
        mobRadarSizeBar=  sharedPreferences.getInt("mobRadarSizeBar", 10);
        mobIconWidthHeightBar=  sharedPreferences.getInt("mobIconWidthHeightBar", 50);

        chestIconWidthHeightBar = sharedPreferences.getInt("chestIconWidthHeightBar", 50);

        radarScaleBar  = sharedPreferences.getFloat("radarScaleBar", 2.3f);
        radarSizeWidthHeightBar =  sharedPreferences.getInt("radarSizeWidthHeightBar", 200);

        radarMiddleCircleBar = sharedPreferences.getInt("radarMiddleCircleBar",10);
        radarShowTopMost =  sharedPreferences.getBoolean("radarShowTopMost",false);

        radarShowSquare =  sharedPreferences.getBoolean("radarShowSquare",false);
        radarShowCircle = sharedPreferences.getBoolean("radarShowCircle",false);

        radarCircleSquareBorderBar =sharedPreferences.getInt("radarCircleSquareBorderBar", 5);



        radarXBar  = sharedPreferences.getInt("radarXBar",10);
        radarYBar = sharedPreferences.getInt("radarYBar",10);




        floatingWidthHeightBar = sharedPreferences.getInt("floatingWidthHeightBar",115);
        settingsHeightBar = sharedPreferences.getInt("settingsHeightBar",600);
        settingsWidthBar = sharedPreferences.getInt("settingsWidthBar",1200);
        floatingYBar = sharedPreferences.getInt("floatingYBar",10);
        floatingXBar = sharedPreferences.getInt("floatingXBar",10);

        settingsTransparencyBar = sharedPreferences.getInt("settingsTransparencyBar",1);
    }

    private RadarSettings() {
        // Initialize settings here
    }


    public boolean isInChests(String name)
    {
        for (Map.Entry<String, Boolean> entry :chests.entrySet()) {

            if(name.contains(entry.getKey()))
            {
                return entry.getValue();

            }

        }
        return  false;

    }

    public  boolean isInHarvestable(HarvestableType ht) {
        return harvestableTypes.contains(ht);
    }

    public  void updateHarvestableType(List<HarvestableType> h, boolean show) {
        if (show) {
            for (HarvestableType ht : h) {
                if (!harvestableTypes.contains(ht)) {
                    harvestableTypes.add(ht);
                }
            }
        } else {
            for (HarvestableType ht : h) {
                if (harvestableTypes.contains(ht)) {
                    harvestableTypes.remove(ht);
                }
            }
        }
    }

    public static RadarSettings getInstance() {
        if (instance == null) {
            synchronized (RadarSettings.class) {
                if (instance == null) {
                    instance = new RadarSettings();
                }
            }
        }
        return instance;
    }


}