package com.minhui.vpn.PhotonPackageParser.classes;

import com.minhui.vpn.PhotonPackageParser.classes.PhotonCommand;
import com.minhui.vpn.PhotonPackageParser.classes.PhotonPacketParser;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class PhotonPacket {
    private PhotonPacketParser parent;
    private ByteBuffer payload;
    private int peerId;
    private int flags;
    private int commandCount;
    private long timestamp;
    private long challenge;
    private List<PhotonCommand> commands = new ArrayList<>();

    public PhotonPacket(PhotonPacketParser parent, ByteBuffer buff) {
        this.parent = parent;
        this.payload = buff;
        this.peerId = 0;
        this.flags = 0;
        this.commandCount = 0;
        this.timestamp = 0;
        this.challenge = 0;

        parsePacket();
    }

    private void parsePhotonHeader() {

        payload.order(ByteOrder.BIG_ENDIAN);

        this.peerId = this.payload.getShort();
        payload.order(ByteOrder.nativeOrder());

        this.flags = this.payload.get();
        this.commandCount = this.payload.get();
        payload.order(ByteOrder.BIG_ENDIAN);
        this.timestamp = this.payload.getInt();
        this.challenge = this.payload.getInt();
        payload.order(ByteOrder.nativeOrder());
    }

    private void parsePacket() {
        parsePhotonHeader();

        for (int i = 0; i < this.commandCount; i++) {
            this.commands.add(new PhotonCommand(parent, this.payload));
        }
    }
}