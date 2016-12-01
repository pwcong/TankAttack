package me.pwcong.tankattack.main.param;

/**
 * Created by Pwcong on 2016/11/30.
 */

public class Vector2 {

    public static final Vector2 UP = new Vector2(0,-1);
    public static final Vector2 DOWN = new Vector2(0,1);
    public static final Vector2 LEFT = new Vector2(-1,0);
    public static final Vector2 RIGHT = new Vector2(1,0);

    private float x;
    private float y;

    public Vector2() {
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
