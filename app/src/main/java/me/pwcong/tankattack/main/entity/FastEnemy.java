package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;

import java.util.Map;

/**
 * Created by Pwcong on 2016/12/1.
 */

public class FastEnemy extends BaseEnemy {


    public FastEnemy(int flag, int life, float posX, float posY, float screenWidth, float screenHeight, Map<String, Bitmap> simpleEnemy, float speed) {
        super(flag, life, posX, posY, screenWidth, screenHeight, simpleEnemy, speed);
    }

    @Override
    protected void initVariable() {
        super.initVariable();

        fireDuration = 70;
        moveDuration = 70;

    }
}
