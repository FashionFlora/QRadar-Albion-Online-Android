package com.minhui.vpn.PhotonPackageParser.Events;

import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class NewChestEvent   {






    public void addNewChest(HashMap<Object, Object> parameters) {



        int  id  = Utils.getNumber(parameters.get(0));


        float[] chestPosition = Utils.getFloats(parameters.get(1));

        String chestName = String.valueOf(parameters.get(3)).toLowerCase();

        if(chestName.toLowerCase().contains("mist"))
        {
             chestName = String.valueOf(parameters.get(3));

             if(chestName.contains("green"))
             {
                 chestName = "standard";
             }
             else if(chestName.contains("blue"))
             {
                 chestName = "uncommon";
             }
             else if(chestName.contains("purple"))
             {
                 chestName = "rare";
             }
             else if(chestName.contains("yellow"))
             {
                 chestName = "legendary";
             }
        }
        if(chestName.contains("green"))
        {
            chestName = "standard";
        }
        else if(chestName.contains("blue"))
        {
            chestName = "uncommon";
        }
        else if(chestName.contains("purple"))
        {
            chestName = "rare";
        }
        else if(chestName.contains("yellow"))
        {
            chestName = "legendary";
        }

        MainHandler.getInstance().chestHandler.addChest(id,chestPosition[0],chestPosition[1], chestName);



    }




    public void removeChest(HashMap<Object, Object> parameters ) {


        int id = Utils.getNumber(parameters.get(0));

        MainHandler.getInstance().chestHandler.removeChest(id);



    }


}
