package com.minhui.vpn.Handlers.HandlerItem;

public class Harvestable {
    private int id;
    private int type;
    private int tier;
    private float posX;
    private float posY;
    private int charges;
    private int size;
    private String enchant;

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

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getEnchant() {
        return this.charges;
    }

    public void setEnchant(String enchant) {
        this.enchant = enchant;
    }

    public Harvestable(int id, int type, int tier, float posX, float posY, int charges, int size) {
        this.id = id;
        this.type = type;
        this.tier = tier;
        this.posX = posX;
        this.posY = posY;
        this.charges = charges;
        this.size = size;
    }
}