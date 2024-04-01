package com.minhui.vpn.PhotonPackageParser;

import android.util.Log;

public class Utils
{
    public static  int MaxDistance = 80;
    public static  int MaxPlayer = 100;

    public static int getNumber(Object object)
    {
        int number = 0;

        try
        {
            if (object != null)
            {
                if(object instanceof Integer)
                {
                    number =  (int)object;
                }
                else if(object instanceof  Byte)
                {
                    number = Byte.toUnsignedInt((byte) object);
                }
                else if(object instanceof Long)
                {
                    number = ((Long) object).intValue();
                }
                else
                {
                    number = Short.toUnsignedInt((short) object);
                }
            }
        }
        catch (Exception ex)
        {
            Log.i ("Utils","getNumber Error:" + ex);
        }
        return number;
    }

    public static int bytesToInt(byte[] bytes, int startIndex)
    {
        return (bytes[startIndex] & 0xFF) |
                ((bytes[startIndex + 1] & 0xFF) << 8) |
                ((bytes[startIndex + 2] & 0xFF) << 16) |
                ((bytes[startIndex + 3] & 0xFF) << 24);
    }

    public static String getString(Object object)
    {
        try
        {
            return String.valueOf(object);
        }
        catch(Exception ex)
        {
            Log.i ("Utils","getString Error:" + ex);
            return null;
        }
    }

    public static boolean getBoolean(Object object)
    {
        try
        {
            if (object != null)
            {
                return (boolean)object;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            Log.i ("Utils","getBoolean Error:" + ex);
            return false;
        }
    }

    public static float getFloat(Object object)
    {
        float number = 0;

        if (object != null)
        {
            try
            {
                number = (float)object;
            }
            catch (Exception ex)
            {
                Log.i ("Utils","getFloat Error:" + ex);
            }
        }

        return number;
    }

    public static long getLong(Object object)
    {
        long number = 0;

        if (object != null)
        {
            try
            {
                number = (long)object;
            }
            catch (Exception ex)
            {
                Log.i ("Utils","getLong Error:" + ex);
            }
        }

        return number;
    }

    public static float[] getFloats(Object object)
    {
        Object[] objectArray = (Object[])object;
        float[] floatArray = new float[objectArray.length];

        for (int i = 0; i < objectArray.length; i++)
        {
            if (objectArray[i] instanceof Float)
            {
                floatArray[i] = (float) objectArray[i];
            }
        }
        return floatArray;
    }

    public static byte[] getByteArray(Object object)
    {
        byte[] objectArray = (byte[])object;
        return objectArray;
    }

    public static int[] getKnownArray(Object object)
    {
        if(object != null)
        {
            if(object instanceof byte[])
            {
                byte[] objectArray = (byte[])object;
                int[] getInt = new int[objectArray.length];

                for (int i = 0; i < objectArray.length; i++)
                {
                    byte temp  = objectArray[i];
                    getInt[i] = Byte.toUnsignedInt(temp);
                }

                return getInt;
            }
            else if(object instanceof short[])
            {
                short[] objectArray = (short[])object;
                int[] getInt = new int[objectArray.length];

                for (int i = 0; i < objectArray.length; i++)
                {
                    short temp  = objectArray[i];
                    getInt[i] = Short.toUnsignedInt(temp);
                }

                return getInt;
            }
            else
            {
                Object[] objectArray = (Object[])object;
                int[] getInt = new int[objectArray.length];

                for (int i = 0; i < objectArray.length; i++)
                {
                    if (objectArray[i] instanceof Byte)
                    {
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
                        getInt[i] = temp;
                    }
                }
                return getInt;
            }
        }
        else
        {
            return null;
        }
    }

    public static double calculateDistance(double lpX, double lpY, double posX, double posY)
    {
        double deltaX = lpX - posX;
        double deltaY = lpY - posY;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
