package me.pwcong.tankattack.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;
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

    private Map<String,Map<String,Bitmap>> largeEnemy;

    private List<Bitmap> boom;

    private Bitmap bombObject;


    private BitmapManager(){

        player = new ConcurrentHashMap<>();
        bullet = new ConcurrentHashMap<>();
        simpleEnemy = new ConcurrentHashMap<>();
        fastEnemy = new ConcurrentHashMap<>();
        largeEnemy = new ConcurrentHashMap<>();
        boom = new ArrayList<>();

    }

    public static synchronized BitmapManager getInstance() {
        if (instance == null)
            instance = new BitmapManager();

        return instance;
    }

    public void loadBitmap(){

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

        fastEnemy.put(BaseEntity.STATUS_RIGHT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy2_r),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        fastEnemy.put(BaseEntity.STATUS_LEFT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy2_l),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        fastEnemy.put(BaseEntity.STATUS_UP,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy2_u),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        fastEnemy.put(BaseEntity.STATUS_DOWN,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy2_d),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        Map<String,Bitmap> enemy3_1 = new ConcurrentHashMap<>();
        enemy3_1.put(BaseEntity.STATUS_RIGHT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_1_r),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_1.put(BaseEntity.STATUS_LEFT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_1_l),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_1.put(BaseEntity.STATUS_UP,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_1_u),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_1.put(BaseEntity.STATUS_DOWN,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_1_d),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        Map<String,Bitmap> enemy3_2 = new ConcurrentHashMap<>();
        enemy3_2.put(BaseEntity.STATUS_RIGHT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_2_r),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_2.put(BaseEntity.STATUS_LEFT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_2_l),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_2.put(BaseEntity.STATUS_UP,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_2_u),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_2.put(BaseEntity.STATUS_DOWN,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_2_d),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        Map<String,Bitmap> enemy3_3 = new ConcurrentHashMap<>();
        enemy3_3.put(BaseEntity.STATUS_RIGHT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_3_r),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_3.put(BaseEntity.STATUS_LEFT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_3_l),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_3.put(BaseEntity.STATUS_UP,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_3_u),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_3.put(BaseEntity.STATUS_DOWN,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_3_d),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        Map<String,Bitmap> enemy3_4 = new ConcurrentHashMap<>();
        enemy3_4.put(BaseEntity.STATUS_RIGHT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_4_r),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_4.put(BaseEntity.STATUS_LEFT,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_4_l),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_4.put(BaseEntity.STATUS_UP,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_4_u),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        enemy3_4.put(BaseEntity.STATUS_DOWN,
                BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.enemy3_4_d),Const.BITMAP_SCALE,Const.BITMAP_SCALE));


        largeEnemy.put("1",enemy3_1);
        largeEnemy.put("2",enemy3_2);
        largeEnemy.put("3",enemy3_3);
        largeEnemy.put("4",enemy3_4);

        boom.add(BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.blast1),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        boom.add(BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.blast2),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        boom.add(BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.blast3),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        boom.add(BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.blast4),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        boom.add(BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.blast5),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        boom.add(BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.blast6),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        boom.add(BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.blast7),Const.BITMAP_SCALE,Const.BITMAP_SCALE));
        boom.add(BitmapUtils.zoom(BitmapFactory.decodeResource(App.getInstance().getResources(), R.raw.blast8),Const.BITMAP_SCALE,Const.BITMAP_SCALE));

        bombObject = BitmapFactory.decodeResource(App.getInstance().getResources(),R.raw.obj_bomb);


    }


    public Bitmap getBombObject() {
        return bombObject;
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

    public Map<String, Bitmap> getFastEnemy() {
        return fastEnemy;
    }

    public Map<String, Map<String, Bitmap>> getLargeEnemy() {
        return largeEnemy;
    }

    public List<Bitmap> getBoom() {
        return boom;
    }

}
