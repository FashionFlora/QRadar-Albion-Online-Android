package com.minhui.vpn.PhotonPackageParser.Events;

import com.minhui.vpn.Handlers.MainHandler;
import com.minhui.vpn.PhotonPackageParser.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class NewHarvestableEvent   {




    public void update(HashMap<Object, Object> parameters) {

        int id =  Utils.getNumber(parameters.get(3));
        int count  =  Utils.getNumber(parameters.get(5));


        MainHandler.getInstance().harvestablesHandler.updateHarvestable(id,count);

    }


    public void addNewHarvestable(HashMap<Object, Object> parameters) {




        int id = Utils.getNumber(parameters.get(0));


        int type = Utils.getNumber(parameters.get(5));
        int tier = Utils.getNumber(parameters.get(7));

        float[] location = Utils.getFloats(parameters.get(8));





        int  enchant = 0;
        int  size = 0;
        try
        {
             size = Utils.getNumber(parameters.get(10));
        }
        catch(Exception ignored)
        {

        }

        try {

            enchant = Utils.getNumber(parameters.get(11));
        }
        catch(Exception ignored){}


        MainHandler.getInstance().harvestablesHandler.addHarvestable(id, type, tier, location[0], location[1], enchant, size);

    }
    public void addNewHarvestableSimple(HashMap<Object, Object> parameters) {

        int[] a0 = Utils.getKnownArray(parameters.get(0));
        if(a0.length==0)
        {
            return;

        }
        int[] a1 = Utils.getKnownArray(parameters.get(1));
        int[] a2 = Utils.getKnownArray(parameters.get(2));
        float[] a3 =  Utils.getFloats(parameters.get(3));
        int[] a4 = Utils.getKnownArray(parameters.get(4));
        for (int i = 0; i < a0.length; i++)
        {
            int id = a0[i];
            int type = a1[i];
            int tier = a2[i];
            float posX = a3[i * 2];
            float posY = a3[i * 2 + 1];
            int count = a4[i];

            MainHandler.getInstance().harvestablesHandler.addHarvestable(id, type, tier, posX, posY, 0, count);



        }



    }
    public void removeHarvestable(HashMap<Object, Object> parameters ) {


        MainHandler.getInstance().harvestablesHandler.removeHarvestable(Utils.getNumber(parameters.get(0)));


    }


}
