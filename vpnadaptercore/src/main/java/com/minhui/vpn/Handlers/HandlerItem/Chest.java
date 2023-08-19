package com.minhui.vpn.Handlers.HandlerItem;


public class Chest {
    private float posX;
    private float posY;
    private String name;
    private int id;

    public Chest(int id, float posX, float posY, String name) {
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}