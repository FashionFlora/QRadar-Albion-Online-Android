package com.minhui.vpn.Handlers.HandlerItem;
public class Player {
    public float posX;
    public float posY;
    public String nickname;
    public String guild;
    public String alliance;
    public int id;
    public int health;
    public float speed;


    public float currentHealth;
    public short[] items;
    public float initialHealth;
    public boolean mounted = false;
    public boolean firstMount = false;
    public boolean invalidate = false;

    public Player() {
        posX = 0;
        posY = 0;
        nickname = "";
        guild = "";
        alliance = "";
        id = 0;
    }



    public  Player(float posX, float posY, int id, String nickname, String guildName, float currentHealth, float initialHealth, short[] items) {
        this.posX = posX;
        this.posY = posY;
        this.id = id;
        this.nickname = nickname;
        this.items = items;
        this.guild = guildName;


        this.currentHealth = currentHealth;
        this.initialHealth = initialHealth;
    }

    @Override
    public String toString() {
        return nickname + "(" + id + "):" + guild + " " + alliance + " [" + posX + " " + posY + "]";
    }

    public synchronized float getPosX() {
        return posX;
    }

    public  synchronized float getPosY() {
        return this.posY;

    }


    public   void setPosX(float posX) {
        this.posX = posX;
    }


    public  void setPosY(float posY) {
        this.posY = posY;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public String getAlliance() {
        return alliance;
    }

    public void setAlliance(String alliance) {
        this.alliance = alliance;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }


    public void setCurrentHealth(float currentHealth) {
    }

    public void setInitialHealth(float initialHealth) {
    }

    public boolean isFirstMount() {

        return firstMount;

    }

    public void setMounted(boolean mounted) {

        this.mounted = mounted;

    }

    public boolean getMounted() {

        return  mounted;

    }
}