package me.pwcong.tankattack.config;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class Const {

    public static final String PRE_NAME = "tank_attack";
    public static final String KEY_LIFE_TIMES = "life_times";
    public static final String KEY_ENEMY_COUNTS = "enemy_counts";

    public static int FPS = 30;

    public static final float BITMAP_SCALE = 0.7f;

    public static float PLAYER_SPEED = 10;
    public static float SIMPLE_ENEMY_SPEED = 6;
    public static float FAST_ENEMY_SPEED = 14;
    public static float LARGE_ENEMY_SPEED = 3;

    public static float PLAYER_BULLET_SPEED = 26;
    public static float SIMPLE_BULLET_SPEED = 16;
    public static float FAST_BULLET_SPEED = 24;
    public static float LARGE_BULLET_SPEED = 12;

    public static int PLAYER_FIRE_SPEED = 500;
    public static float SIMPLE_ENEMY_FIRE_SALT = 0.7f;
    public static float FAST_ENEMY_FIRE_SALT = 0.65f;
    public static float LARGE_ENEMY_FIRE_SALT = 0.6f;



    private Const(){
        throw new RuntimeException("(●ˇ∀ˇ●)");
    }
}
