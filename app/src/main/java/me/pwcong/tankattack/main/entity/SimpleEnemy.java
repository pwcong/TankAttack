package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Map;

import me.pwcong.tankattack.config.Const;

/**
 * Created by Pwcong on 2016/11/30.
 */

public class SimpleEnemy extends BaseEnemy{


    public SimpleEnemy(int flag, int life, float posX, float posY, float screenWidth, float screenHeight, float speed, Map<String, Bitmap> enemy) {
        super(flag, life, posX, posY,screenWidth,screenHeight,speed);
        setEnemy(enemy);
        setSelfWidth(getEnemy().get(STATUS_UP).getWidth());
        setSelfHeight(getEnemy().get(STATUS_UP).getHeight());
    }

}
