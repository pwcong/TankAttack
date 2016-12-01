package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;

import java.util.Map;

import me.pwcong.tankattack.config.Const;

/**
 * Created by Pwcong on 2016/12/1.
 */

public class FastEnemy extends BaseEnemy {


    public FastEnemy(int flag, int life, float posX, float posY, float screenWidth, float screenHeight, float speed, Map<String, Bitmap> enemy) {
        super(flag, life, posX, posY, screenWidth, screenHeight, speed);

        setEnemy(enemy);
        setSelfWidth(enemy.get(STATUS_UP).getWidth());
        setSelfHeight(enemy.get(STATUS_UP).getHeight());

    }

    @Override
    protected void initVariable() {
        super.initVariable();

        fireDuration = 70;
        fireSalt = Const.FAST_ENEMY_FIRE_SALT;
        moveDuration = 70;


    }
}
