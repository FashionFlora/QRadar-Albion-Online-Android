package com.minhui.vpn.PhotonPackageParser;

import java.lang.reflect.Array;

public class Utils {
    public static int getNumber(Object object)
    {

        int number = 0;


        if(object instanceof Integer)
        {
            number =  (int)object;
        }
        else if(object instanceof  Byte)
        {
            number = Byte.toUnsignedInt((byte) object);
        }
        else
        {
            number = Short.toUnsignedInt((short) object);
        }
        return number;

    }

    public static int bytesToInt(byte[] bytes, int startIndex) {
        return (bytes[startIndex] & 0xFF) |
                ((bytes[startIndex + 1] & 0xFF) << 8) |
                ((bytes[startIndex + 2] & 0xFF) << 16) |
                ((bytes[startIndex + 3] & 0xFF) << 24);
    }

    public static float[] getFloats(Object object)
    {

        Object[] objectArray = (Object[])object;
        float[] floatArray = new float[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            if (objectArray[i] instanceof Float) {
                floatArray[i] = (float) objectArray[i];

            }
        }
        return floatArray;


    }


    /*
    public static <T> T[] getArrayValues(Object object, Class<T> tClass) {
        Object[] objectArray = (Object[]) object;
        T[] array = (T[]) Array.newInstance(tClass, objectArray.length);
        for (int i = 0; i < objectArray.length; i++) {
            if (tClass.isInstance(objectArray[i])) {
                array[i] = tClass.cast(objectArray[i]);
            }
        }
        return array;
    }

     */


    public static int[] getInts(Object object)
    {

        Object[] objectArray = (Object[])object;
        int[] getInt = new int[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            if (objectArray[i] instanceof Byte) {
                byte temp  = (byte)objectArray[i];
                getInt[i] = Byte.toUnsignedInt(temp);

            }
            else if(objectArray[i] instanceof Short)
            {
                short temp  = (short)objectArray[i];
                getInt[i] = Short.toUnsignedInt(temp);
            }
            else if(objectArray[i] instanceof Integer)
            {
                int temp  = (int)objectArray[i];
                getInt[i] =temp;
            }
        }
        return getInt;


    }

    public static int[] getKnownArray(Object object)
    {

        if(object instanceof  byte[])
        {
            byte[] objectArray = (byte[])object;
            int[] getInt = new int[objectArray.length];

            for (int i = 0; i < objectArray.length; i++) {
                    byte temp  = (byte)objectArray[i];
                    getInt[i] = Byte.toUnsignedInt(temp);

                }
            return getInt;

        }



        if(object instanceof  short[])
        {
            short[] objectArray = (short[])object;
            int[] getInt = new int[objectArray.length];
            for (int i = 0; i < objectArray.length; i++) {
                short temp  = (short)objectArray[i];
                getInt[i] = Short.toUnsignedInt(temp);

            }
            return getInt;

        }

        return  (int[])getInts(object);




    }




}
