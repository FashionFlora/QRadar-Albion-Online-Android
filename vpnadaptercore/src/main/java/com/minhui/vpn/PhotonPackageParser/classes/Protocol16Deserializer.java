package com.minhui.vpn.PhotonPackageParser.classes;

import android.util.Log;

import com.minhui.vpn.PhotonPackageParser.enumerations.Protocol16Type;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Protocol16Deserializer {
    private static HashMap<Byte, Protocol16Type> protocol16TypeMap;

    static {
        protocol16TypeMap = new HashMap<>();
        protocol16TypeMap.put((byte) 0, Protocol16Type.Unknown);
        protocol16TypeMap.put((byte) 42, Protocol16Type.Null);
        protocol16TypeMap.put((byte) 68, Protocol16Type.Dictionary);
        protocol16TypeMap.put((byte) 97, Protocol16Type.StringArray);
        protocol16TypeMap.put((byte) 98, Protocol16Type.Byte);
        protocol16TypeMap.put((byte) 100, Protocol16Type.Double);
        protocol16TypeMap.put((byte) 101, Protocol16Type.EventData);
        protocol16TypeMap.put((byte) 102, Protocol16Type.Float);
        protocol16TypeMap.put((byte) 105, Protocol16Type.Integer);
        protocol16TypeMap.put((byte) 104, Protocol16Type.Hashtable);
        protocol16TypeMap.put((byte) 107, Protocol16Type.Short);
        protocol16TypeMap.put((byte) 108, Protocol16Type.Long);
        protocol16TypeMap.put((byte) 110, Protocol16Type.IntegerArray);
        protocol16TypeMap.put((byte) 111, Protocol16Type.Boolean);
        protocol16TypeMap.put((byte) 112, Protocol16Type.OperationResponse);
        protocol16TypeMap.put((byte) 113, Protocol16Type.OperationRequest);
        protocol16TypeMap.put((byte) 115, Protocol16Type.String);
        protocol16TypeMap.put((byte) 120, Protocol16Type.ByteArray);
        protocol16TypeMap.put((byte) 121, Protocol16Type.Array);
        protocol16TypeMap.put((byte) 122, Protocol16Type.ObjectArray);
    }

    public static Object deserialize(ByteBuffer buffer, byte typeCode) {
        Protocol16Type type = protocol16TypeMap.get(typeCode);
        if (type == null) {
            return null;

         //   throw new IllegalArgumentException("Type code: " + typeCode + " not implemented.");
        }




        switch (type) {
            case Unknown:
            case Null:
                return null;
            case Byte:
                return deserializeByte(buffer);
            case Boolean:
                return deserializeBoolean(buffer);
            case Short:
                return deserializeShort(buffer);
            case Integer:
                return deserializeInteger(buffer);
            case IntegerArray:
                return deserializeIntegerArray(buffer);
            case Double:
                return deserializeDouble(buffer);
            case Long:
                return deserializeLong(buffer);
            case Float:
                return deserializeFloat(buffer);
            case String:
                return deserializeString(buffer);
            case StringArray:
                return deserializeStringArray(buffer);
            case ByteArray:
                return deserializeByteArray(buffer);
            case EventData:
                return deserializeEventData(buffer);
            case Dictionary:
                return deserializeDictionary(buffer);
            case Array:
                return deserializeArray(buffer);
            case OperationResponse:
                return deserializeOperationResponse(buffer);
            case OperationRequest:
                return deserializeOperationRequest(buffer);
            case Hashtable:
                return deserializeHashtable(buffer);
            case ObjectArray:
                return deserializeObjectArray(buffer);
            default:
                throw new IllegalArgumentException("Type code: " + typeCode + " not implemented.");
        }
    }

    private static byte deserializeByte(ByteBuffer input) {
        input.order(ByteOrder.BIG_ENDIAN);
        byte arraySize = (input.get()) ;
        input.order(ByteOrder.nativeOrder());
        return arraySize;
    }

    private static boolean deserializeBoolean(ByteBuffer input) {
        input.order(ByteOrder.BIG_ENDIAN);
        boolean p = input.get() != 0;
        input.order(ByteOrder.nativeOrder());
        return p;
    }

    private static short deserializeShort(ByteBuffer input) {


        input.order(ByteOrder.BIG_ENDIAN);
        short p = (short) (input.getShort() & 0xFFFF);
        input.order(ByteOrder.nativeOrder());


        return p;

    }

    private static int deserializeInteger(ByteBuffer input) {
        input.order(ByteOrder.BIG_ENDIAN);
        int p =  input.getInt() & 0xFFFFFFFF;
        input.order(ByteOrder.nativeOrder());
        return p;
    }

    private static int[] deserializeIntegerArray(ByteBuffer input) {
        int size = deserializeInteger(input);
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = deserializeInteger(input);
        }

        return result;
    }

    private static double deserializeDouble(ByteBuffer input) {

        input.order(ByteOrder.BIG_ENDIAN);
        double p = input.getDouble();
        input.order(ByteOrder.nativeOrder());
        return p;
    }

    private static long deserializeLong(ByteBuffer input) {

        input.order(ByteOrder.BIG_ENDIAN);
        long p = input.getLong();
        input.order(ByteOrder.nativeOrder());
        return p;
    }

    private static float deserializeFloat(ByteBuffer input) {

        input.order(ByteOrder.BIG_ENDIAN);
        float p = input.getFloat();
        input.order(ByteOrder.nativeOrder());
        return p;
    }

    private static String deserializeString(ByteBuffer input) {
        short stringSize = deserializeShort(input);
        if (stringSize == 0) {
            return "";
        }
        byte[] bytes = new byte[stringSize];
        input.get(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private static byte[] deserializeByteArray(ByteBuffer input) {


        input.order(ByteOrder.BIG_ENDIAN);
        int arraySize = input.getInt() & 0xFFFFFFFF;
        input.order(ByteOrder.nativeOrder());


        byte[] bytes = new byte[arraySize];
        input.get(bytes);
        return bytes;
    }

    private static Object[] deserializeArray(ByteBuffer input) {
        short size = deserializeShort(input);
        byte typeCode = deserializeByte(input);
        Object[] result = new Object[size];



        for (int i = 0; i < size; i++) {
            result[i] = deserialize(input, typeCode);


        }







        return result;
    }

    private static String[] deserializeStringArray(ByteBuffer input) {
        short size = deserializeShort(input);
        String[] result = new String[size];

        for (int i = 0; i < size; i++) {
            result[i] = deserializeString(input);
        }


        return result;
    }

    private static Object[] deserializeObjectArray(ByteBuffer input) {
        short tableSize = deserializeShort(input);
        Object[] result = new Object[tableSize];

        for (int i = 0; i < tableSize; i++) {
            byte typeCode = deserializeByte(input);
            result[i] = deserialize(input, typeCode);
        }

        return result;
    }

    private static HashMap<Object, Object> deserializeHashtable(ByteBuffer input) {
        short tableSize = deserializeShort(input);
        return deserializeDictionaryElements(input, tableSize, (byte) 0, (byte) 0);
    }

    private static HashMap<Object, Object> deserializeDictionary(ByteBuffer input) {
        byte keyTypeCode = deserializeByte(input);
        byte valueTypeCode = deserializeByte(input);
        short dictionarySize = deserializeShort(input);
        return deserializeDictionaryElements(input, dictionarySize, keyTypeCode, valueTypeCode);
    }

    private static HashMap<Object, Object> deserializeDictionaryElements(ByteBuffer input, short dictionarySize,
                                                                         byte keyTypeCode, byte valueTypeCode) {
        HashMap<Object, Object> output = new HashMap<>();

        for (int i = 0; i < dictionarySize; i++) {
            Object key = deserialize(input,
                    (keyTypeCode == 0 || keyTypeCode == 42) ? deserializeByte(input) : keyTypeCode);
            Object value = deserialize(input,
                    (valueTypeCode == 0 || valueTypeCode == 42) ? deserializeByte(input) : valueTypeCode);
            output.put(key, value);
        }

        return output;
    }

    public static HashMap<Object, Object> deserializeOperationRequest(ByteBuffer input) {

        HashMap<Object, Object> parameters = deserializeParameterTable(input);



        return parameters;
    }

    public static HashMap<Object, Object> deserializeOperationResponse(ByteBuffer input) {
     //   byte operationCode = deserializeByte(input);
        input.order(ByteOrder.BIG_ENDIAN);
        short returnCode = deserializeShort(input);
        String debugMessage = (String) deserialize(input, deserializeByte(input));
        HashMap<Object, Object> parameters = deserializeParameterTable(input);
        input.order(ByteOrder.nativeOrder());


        return parameters;
    }

    public static HashMap<Object, Object> deserializeEventData(ByteBuffer input) {

        input.order(ByteOrder.BIG_ENDIAN);
        HashMap<Object, Object> parameters = deserializeParameterTable(input);
        input.order(ByteOrder.nativeOrder());

        return parameters;
    }

    private static HashMap<Object, Object> deserializeParameterTable(ByteBuffer input) {




        input.order(ByteOrder.BIG_ENDIAN);

        short tableSize = (short) (input.getShort() & 0xFFFF);
        input.order(ByteOrder.nativeOrder());
        HashMap<Object, Object> table = new HashMap<>();
        for (int i = 0; i < tableSize; i++) {

            input.order(ByteOrder.BIG_ENDIAN);
            byte key = (byte) (input.get() & 0xFF);
            byte valueTypeCode =  input.get() ;
            int unsignedKey = key & 0xFF;


            input.order(ByteOrder.nativeOrder());


            Object value = deserialize(input, valueTypeCode);


            table.put(unsignedKey, value);



        }

        return table;
    }
}