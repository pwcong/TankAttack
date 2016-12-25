package me.pwcong.tankattack.main.entity;

import android.graphics.Canvas;

/**
 * Created by Pwcong on 2016/11/29.
 */

public abstract class BaseEntity {

    public static final int FLAG_PLAYER = 1;
    public static final int FLAG_OTHER = 0;
    public static final int FLAG_ENEMY = -1;
    public static final int FLAG_OBJECT = 2;

    public static final String STATUS_UP = "up";
    public static final String STATUS_DOWN = "down";
    public static final String STATUS_LEFT = "left";
    public static final String STATUS_RIGHT = "right";

    private boolean isDead = false;

    private int flag;

    private int life;

    private float posX;
    private float posY;

    private float screenWidth;
    private float screenHeight;

    private float selfWidth;
    private float selfHeight;

    public BaseEntity(int flag, int life, float posX, float posY, float screenWidth, float screenHeight) {
        this.flag = flag;
        this.life = life;
        this.posX = posX;
        this.posY = posY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    protected abstract void initVariable();

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
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

    public float getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(float screenWidth) {
        this.screenWidth = screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(float screenHeight) {
        this.screenHeight = screenHeight;
    }

    public float getSelfWidth() {
        return selfWidth;
    }

    public void setSelfWidth(float selfWidth) {
        this.selfWidth = selfWidth;
    }

    public float getSelfHeight() {
        return selfHeight;
    }

    public void setSelfHeight(float selfHeight) {
        this.selfHeight = selfHeight;
    }

    interface Behavior{

        void onDraw(Canvas canvas);

        void onLogic();

    }

    public static String getRandomStatus(){

        int random = Math.round((float)(Math.random()*3));

        String status;

        switch (random){
            case 0:status = STATUS_UP;break;
            case 1:status = STATUS_DOWN;break;
            case 2:status = STATUS_LEFT;break;
            case 3:status = STATUS_RIGHT;break;
            default:status = STATUS_UP;break;

        }
        return status;
    }


}
