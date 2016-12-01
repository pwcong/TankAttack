package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Map;

import me.pwcong.tankattack.config.Const;

/**
 * Created by Pwcong on 2016/12/1.
 */

public class LargeEnemy extends BaseEnemy{

    private Map<String,Map<String,Bitmap>> enemyMap;

    public LargeEnemy(int flag, int life, float posX, float posY, float screenWidth, float screenHeight,
                      float speed,Map<String,Map<String,Bitmap>> enemyMap) {
        super(flag, life, posX, posY,screenWidth,screenHeight,speed);

        this.enemyMap = enemyMap;

        setEnemy(enemyMap.get(String.valueOf(enemyMap.size() - getLife()+1)));
        setSelfHeight(getEnemy().get(STATUS_UP).getHeight());
        setSelfWidth(getEnemy().get(STATUS_UP).getWidth());

    }

    @Override
    protected void initVariable() {
        super.initVariable();

        fireDuration = 60;
        fireSalt = Const.LARGE_ENEMY_FIRE_SALT;
        moveDuration = 60;

    }

    @Override
    protected void autoCheckLife() {
        if(getLife()<=0)
            setDead(true);
        else {
            setEnemy(enemyMap.get(String.valueOf(enemyMap.size() - getLife() + 1)));
        }
    }
}
