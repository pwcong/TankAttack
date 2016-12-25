package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;

/**
 * Created by Pwcong on 2016/12/25.
 */

public class BombObject extends BaseObject {
    public BombObject(int flag, int life, float posX, float posY, float screenWidth, float screenHeight, Bitmap bitmap) {
        super(flag, life, posX, posY, screenWidth, screenHeight, bitmap);
    }
}
