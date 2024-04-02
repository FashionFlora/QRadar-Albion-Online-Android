package com.minhui.vpn.PhotonPackageParser.enumerations;

public enum FactionCodes
{
    FRIENDLY(0),
     MARTLOCK(1),
    LYMHURST(2),
    BRIDGEWATCH(3),
    FORTSTERLING(4),
    THETFORD(5),
    CAERLEON(6),
    HOSTILE(255);

    private final int value;

    FactionCodes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
