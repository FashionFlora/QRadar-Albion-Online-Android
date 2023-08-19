package com.minhui.vpn.PhotonPackageParser.classes;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class PhotonCommand {
    private PhotonPacketParser parent;
    private ByteBuffer payload;
    private int commandType;
    private int channelId;
    private int commandFlags;
    private int commandLength;
    private long sequenceNumber;
    private int messageType;

    public PhotonCommand(PhotonPacketParser parent, ByteBuffer payload) {
        this.parent = parent;
        this.payload = payload;
        this.commandType = 0;
        this.channelId = 0;
        this.commandFlags = 0;
        this.commandLength = 0;
        this.sequenceNumber = 0;
        this.messageType = 0;

        parseCommand();
    }

    private void parseCommandHeader() {
        this.commandType = this.payload.get();

        this.channelId = this.payload.get();

        this.commandFlags = this.payload.get();

        this.payload.position(this.payload.position() + 1);

        payload.order(ByteOrder.BIG_ENDIAN);
        this.commandLength = this.payload.getInt();

        this.sequenceNumber = this.payload.getInt();


        payload.order(ByteOrder.nativeOrder());
        byte[] slicedPayload = new byte[this.commandLength - 12];
        this.payload.get(slicedPayload);
        this.payload = ByteBuffer.wrap(slicedPayload);

    }


    private void parseCommand() {
        parseCommandHeader();

        switch (this.commandType) {
            case 7:
                this.payload.position(this.payload.position() + 4);
                byte[] slicedPayload = new byte[this.payload.remaining() ];
                this.payload.get(slicedPayload);
                this.payload = ByteBuffer.wrap(slicedPayload);


            case 6:
                parseReliableCommand();
                break;
            case 4:
                break;
        }
    }
    public static String[] bytesToHex(byte[] byteArray) {
        String[] hexArray = new String[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            String hexValue = Integer.toHexString(byteArray[i] & 0xFF);
            hexArray[i] = hexValue.length() == 1 ? "0" + hexValue : hexValue;
        }
        return hexArray;
    }

    private void parseReliableCommand() {
        this.payload.position(this.payload.position() + 1);
        this.messageType = this.payload.get();

        byte[] slicedPayload = new byte[this.payload.remaining()];
        this.payload.get(slicedPayload);
        this.payload = ByteBuffer.wrap(slicedPayload);




        switch (this.messageType) {
            case 2:
                byte operationCode = payload.get();
                parent.onRequest(operationCode , Protocol16Deserializer.deserializeOperationRequest(payload));
                break;
            case 3:
              //  this.data = Protocol16Deserializer.deserializeOperationResponse(this.payload);
             //   this.parent.emit('response', this.data);
                break;
            case 4:

                byte eventCode =  payload.get();

                parent.onEvent(eventCode , Protocol16Deserializer.deserializeEventData(payload));
             //   this.parent.emit('event', this.data);
                break;
        }
    }
}