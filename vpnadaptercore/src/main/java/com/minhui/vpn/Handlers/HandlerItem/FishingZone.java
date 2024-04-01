package com.minhui.vpn.Handlers.HandlerItem;

public class FishingZone
{
    public int id;
    public float posX;
    public float posY;
    private int charges;
    private int fished;
    public String zoneType;

    public float getPosX()
    {
        return posX;
    }

    public void setPosX(float value)
    {
        posX = value;
    }

    public float getPosY()
    {
        return posY;
    }

    public void setPosY(float value)
    {
        posY = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FishingZone(int id, float posX, float posY, int charges, int fished, String zoneType)
    {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.setCharges(charges);
        this.setFished(fished);
        this.zoneType = zoneType;
    }

    public int getFished() {
        return fished;
    }

    public void setFished(int fished) {
        this.fished = fished;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }
}