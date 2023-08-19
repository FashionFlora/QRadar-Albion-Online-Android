package com.minhui.vpn.PhotonPackageParser.enumerations;

public enum Protocol16Type {
    Unknown((byte) 0),
    Null((byte) 42),
    Dictionary((byte) 68),
    StringArray((byte) 97),
    Byte((byte) 98),
    Double((byte) 100),
    EventData((byte) 101),
    Float((byte) 102),
    Integer((byte) 105),
    Hashtable((byte) 104),
    Short((byte) 107),
    Long((byte) 108),
    IntegerArray((byte) 110),
    Boolean((byte) 111),
    OperationResponse((byte) 112),
    OperationRequest((byte) 113),
    String((byte) 115),
    ByteArray((byte) 120),
    Array((byte) 121),
    ObjectArray((byte) 122);

    private final byte value;

    Protocol16Type(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}