package com.minhui.vpn.Handlers.HandlerItem;

public class Mob {
    public int id;
    public int typeId;
    public float posX;
    public float posY;
    public int health;
    public int enchantmentLevel;
    public int rarity;
    public int tier;
    public int type = 2;
    public String name;
    public int exp = 0;

    public Mob(int id, int typeId, float posX, float posY, int health, int enchantmentLevel, int rarity) {
        this.id = id;
        this.typeId = typeId;
        this.posX = posX;
        this.posY = posY;
        this.health = health;
        this.rarity = rarity;
        this.enchantmentLevel = enchantmentLevel;
    }

    @Override
    public String toString() {
        return "id:" + id + " typeId: " + typeId + " posX: " + posX + " posY: " + posY + " health: " + health + " charges: " + enchantmentLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int  getEnchantmentLevel() {
        return enchantmentLevel;
    }

    public void setEnchantmentLevel(int enchantmentLevel) {
        this.enchantmentLevel = enchantmentLevel;
    }


    public int getType() {

        return this.type;

    }

    public int getTier() {
        return  this.tier;

    }

    public String getName() {
        return this.name;

    }
}