package com.minhui.vpn.Handlers.HandlerItem;

public class Harvestable
{
    private int id;
    private int type;
    private int tier;
    private float posX;
    private float posY;
    private int charges;
    private int enchant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(byte tier) {
        this.tier = tier;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges)
    {
        this.charges = charges;
    }

    public int getEnchant() { return this.enchant; }

    public void setEnchant(int enchant) {
        this.enchant = enchant;
    }

    public Harvestable(int id, int type, int tier, float posX, float posY, int charges, int enchant) {
        this.id = id;
        this.type = type;
        this.tier = tier;
        this.posX = posX;
        this.posY = posY;
        this.charges = charges;
        this.enchant = enchant;
    }
}