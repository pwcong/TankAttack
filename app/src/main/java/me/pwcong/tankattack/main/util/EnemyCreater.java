package me.pwcong.tankattack.main.util;

import java.util.Vector;

import me.pwcong.tankattack.config.Const;
import me.pwcong.tankattack.main.entity.BaseEnemy;
import me.pwcong.tankattack.main.entity.BaseEntity;
import me.pwcong.tankattack.main.entity.Bullet;
import me.pwcong.tankattack.main.entity.FastEnemy;
import me.pwcong.tankattack.main.entity.LargeEnemy;
import me.pwcong.tankattack.main.entity.SimpleEnemy;
import me.pwcong.tankattack.main.param.Vector2;
import me.pwcong.tankattack.manager.BitmapManager;

/**
 * Created by Pwcong on 2016/12/1.
 */

public class EnemyCreater{

    private EnemyCreater(){

    }

    public static void createSimpleEnemy(final float screenWidth, final float screenHeight, Vector<BaseEnemy> enemies, final Vector<Bullet> bullets){

        Vector2 pos = new Vector2((float)((screenWidth-100)*Math.random()+50),(float)((screenHeight-100)*Math.random()+50));

        final SimpleEnemy simpleEnemy = new SimpleEnemy(BaseEntity.FLAG_ENEMY,1,pos.getX(),pos.getY(),screenWidth,screenHeight, Const.SIMPLE_ENEMY_SPEED,
                BitmapManager.getInstance().getSimpleEnemy());

        simpleEnemy.setOnActionListener(new BaseEnemy.OnActionListener() {
            @Override
            public void onFire() {
                bullets.add(new Bullet(
                                BaseEntity.FLAG_ENEMY,1,
                                simpleEnemy.getPosX()+simpleEnemy.getSelfWidth()/2-20,
                                simpleEnemy.getPosY()+simpleEnemy.getSelfHeight()/2-20,
                                screenWidth,screenHeight,
                                BitmapManager.getInstance().getBullet().get("enemy"),
                                Const.SIMPLE_BULLET_SPEED,
                                simpleEnemy.getStatus()
                        )
                );
            }
        });

        enemies.add(simpleEnemy);


    }

    public static void createFastEnemy(final float screenWidth, final float screenHeight, Vector<BaseEnemy> enemies, final Vector<Bullet> bullets){

        Vector2 pos = new Vector2((float)((screenWidth-100)*Math.random()+50),(float)((screenHeight-100)*Math.random()+50));

        final FastEnemy fastEnemy = new FastEnemy(BaseEntity.FLAG_ENEMY,1,pos.getX(),pos.getY(),screenWidth,screenHeight,
                Const.FAST_ENEMY_SPEED,BitmapManager.getInstance().getFastEnemy());

        fastEnemy.setOnActionListener(new BaseEnemy.OnActionListener() {
            @Override
            public void onFire() {
                bullets.add(new Bullet(
                                BaseEntity.FLAG_ENEMY,1,
                                fastEnemy.getPosX()+fastEnemy.getSelfWidth()/2-20,
                                fastEnemy.getPosY()+fastEnemy.getSelfHeight()/2-20,
                                screenWidth,screenHeight,
                                BitmapManager.getInstance().getBullet().get("enemy"),
                                Const.FAST_BULLET_SPEED,
                                fastEnemy.getStatus()
                        )
                );
            }
        });

        enemies.add(fastEnemy);
    }

    public static void createLargeEnemy(final float screenWidth, final float screenHeight, Vector<BaseEnemy> enemies, final Vector<Bullet> bullets){

        Vector2 pos = new Vector2((float)((screenWidth-100)*Math.random()+50),(float)((screenHeight-100)*Math.random()+50));

        final LargeEnemy largeEnemy = new LargeEnemy(BaseEntity.FLAG_ENEMY,4,pos.getX(),pos.getY(),screenWidth,screenHeight,
                Const.LARGE_ENEMY_SPEED,BitmapManager.getInstance().getLargeEnemy());

        largeEnemy.setOnActionListener(new BaseEnemy.OnActionListener() {
            @Override
            public void onFire() {
                bullets.add(new Bullet(
                                BaseEntity.FLAG_ENEMY,1,
                                largeEnemy.getPosX()+largeEnemy.getSelfWidth()/2-20,
                                largeEnemy.getPosY()+largeEnemy.getSelfHeight()/2-20,
                                screenWidth,screenHeight,
                                BitmapManager.getInstance().getBullet().get("enemy"),
                                Const.LARGE_BULLET_SPEED,
                                largeEnemy.getStatus()
                        )
                );
            }
        });

        enemies.add(largeEnemy);

    }


}
