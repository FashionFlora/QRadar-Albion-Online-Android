package com.minhui.vpn.Handlers.HandlerItem;
public class Player
{
    public int id;
    public float posX;
    public float posY;
    public String nickname;
    public String guild;
    public String alliance;
    public float speed;
    public float angle;
    public float targetposX;
    public float targetposY;
    public float currentHealth;
    public short[] items;
    public boolean mounted = false;
    public boolean firstMount = false;
    public boolean invalidate = false;
    private int faction;
    private float maxEnergy;
    private float currentEnergy;
    private float maxHealth;
    private String cluster;
    private int[] markId;

    public Player()
    {
        posX = 0;
        posY = 0;
        nickname = "";
        guild = "";
        alliance = "";
        id = 0;
    }

    public  Player(int characterId, float posX, float posY, String nickname, String guildName, float currentHealth, float maxHealth, float currentEnergy, float maxEnergy, short[] items)
    {
        this.id = characterId;
        this.posX = posX;
        this.posY = posY;

        this.nickname = nickname;
        this.items = items;
        this.guild = guildName;

        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;

        this.currentEnergy=currentEnergy;
        this.maxEnergy = maxEnergy;
    }

    @Override
    public String toString()
    {
        return nickname + "(" + id + "):" + guild + " " + alliance + " [" + posX + " " + posY + "]";
    }

    public void Update(int characterId, int[] markId, String nickname, String guildName, String cluster, float posX, float posY,
                       float angle, float currentHealth, float maxHealth, float currentEnergy, float maxEnergy, int faction)
    {
        this.id = characterId;
        this.guild = guildName;
        this.nickname = nickname;
        this.markId = markId;
        this.cluster = cluster;
        this.posX = posX;
        this.posY = posY;
        this.angle = angle;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.currentEnergy = currentEnergy;
        this.maxEnergy = maxEnergy;
        this.faction = faction;
    }

    public synchronized float getPosX() {
        return posX;
    }

    public synchronized float getPosY() { return this.posY; }

    public synchronized float getTargetPosX() { return this.targetposX; }
    public synchronized  float getTargetPosY() { return this.targetposY; }

    public  void setPosX(float posX) { this.posX = posX; }
    public  void setPosY(float posY) {
        this.posY = posY;
    }

    public String getNickname() {
        return nickname;
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
    public void setCurrentHealth(float currentHealth)
    {
        this.currentHealth = currentHealth;
    }

    public void setMounted(boolean mounted)
    {
        this.mounted = mounted;
    }

    public boolean getMounted()
    {
        return  mounted;
    }

    public  void setSpeed(float speed) {
        this.speed = speed;
    }
    public  float getSpeed() { return speed; }

    public  void setAngle(float angle) {
        this.angle = angle;
    }
    public  float getAngle() { return angle; }

    public  void setTargetPosX(float targetposX) {
        this.targetposX = targetposX;
    }

    public  void setTargetPosY(float targetposY) {
        this.targetposY = targetposY;
    }

    public float getMaxHealth()
    {
        return maxHealth;
    }
    public float getFaction()
    {
        return faction;
    }
}