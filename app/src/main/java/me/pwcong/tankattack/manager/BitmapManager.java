package me.pwcong.tankattack.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import me.pwcong.tankattack.App;
import me.pwcong.tankattack.R;
import me.pwcong.tankattack.config.Const;
import me.pwcong.tankattack.main.entity.BaseEntity;
import me.pwcong.tankattack.util.BitmapUtils;

/**
 * Created by Pwcong on 2016/11/30.
 */

public class BitmapManager {

    private static BitmapManager instance;

    private Map<String,Bitmap> player;

    private Map<String,Bitmap> bullet;

    private Map<String,Bitmap> simpleEnemy;

    private Map<String,Bitmap> fastEnemy;

    private BitmapManager(){

        player = new ConcurrentHashMap<>();
        bullet = new ConcurrentHashMap<>();
        simpleEnemy = new ConcurrentHashMap<>();
        fastEnemy = new ConcurrentHashMap<>();

    }

    public static synchronized BitmapManager getInstance() {
        if (instance == null)
            instance = new BitmapManager();

        return instance;
    }

    public void loadDrawer(){

        player.put(BaseEntity.STATUS_LEFT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.p2tank_l),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        player.put(BaseEntity.STATUS_RIGHT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.p2tank_r),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        player.put(BaseEntity.STATUS_UP,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.p2tank_u),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        player.put(BaseEntity.STATUS_DOWN,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.p2tank_d),Const.BITMAP_SCALE,Const.BITMAP_SCALE));


        bullet.put("player",BitmapFactory.decodeResource(App.getInstance().getResources(),R.raw.tankmissile));
        bullet.put("enemy",BitmapFactory.decodeResource(App.getInstance().getResources(),R.raw.enemymissile));

        simpleEnemy.put(BaseEntity.STATUS_DOWN,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy1_d),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        simpleEnemy.put(BaseEntity.STATUS_UP,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy1_u),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        simpleEnemy.put(BaseEntity.STATUS_LEFT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy1_l),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        simpleEnemy.put(BaseEntity.STATUS_RIGHT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy1_r),Const.BITMAP_SCALE,Const.BITMAP_SCALE));



    }

    public Map<String, Bitmap> getPlayer() {
        return player;
    }

    public Map<String, Bitmap> getBullet() {
        return bullet;
    }

    public Map<String, Bitmap> getSimpleEnemy() {
        return simpleEnemy;
    }
}
