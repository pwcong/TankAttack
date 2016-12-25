package me.pwcong.tankattack.main.util;

import java.util.Vector;

import me.pwcong.tankattack.main.entity.BaseEnemy;
import me.pwcong.tankattack.main.entity.BaseEntity;
import me.pwcong.tankattack.main.entity.BaseObject;
import me.pwcong.tankattack.main.entity.BombObject;
import me.pwcong.tankattack.main.param.Vector2;
import me.pwcong.tankattack.manager.BitmapManager;


/**
 * Created by Pwcong on 2016/12/25.
 */

public class ObjectCreater {


    private ObjectCreater(){
    }

    public static void createBmonObject(final float screenWidth, final float screenHeight,
                                        Vector<BaseObject> objects, final Vector<BaseEnemy> enemies){

        Vector2 pos = new Vector2((float)((screenWidth-200)*Math.random()+100),(float)((screenHeight-200)*Math.random()+100));

        final BombObject bombObject = new BombObject(BaseEntity.FLAG_OBJECT,1,pos.getX(),pos.getY(),
                screenWidth,screenHeight, BitmapManager.getInstance().getBombObject());

        bombObject.setOnEventListener(new BaseObject.OnEventListener() {
            @Override
            public void doEvent() {
                for(BaseEnemy enemy:enemies)
                    enemy.setDead(true);
            }
        });

        objects.add(bombObject);

    }

}
