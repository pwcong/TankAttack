package me.pwcong.tankattack.main.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import java.util.Vector;

import me.pwcong.tankattack.config.Const;
import me.pwcong.tankattack.main.controller.BaseController;
import me.pwcong.tankattack.main.entity.BaseEntity;
import me.pwcong.tankattack.main.entity.Bullet;
import me.pwcong.tankattack.main.entity.FastEnemy;
import me.pwcong.tankattack.main.entity.LargeEnemy;
import me.pwcong.tankattack.main.entity.Player;
import me.pwcong.tankattack.main.entity.BaseEnemy;
import me.pwcong.tankattack.main.entity.SimpleEnemy;
import me.pwcong.tankattack.manager.BitmapManager;
import me.pwcong.tankattack.manager.SoundManager;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class FirstScene extends BaseScene implements BaseController.FirstScene{

    public final String TAG = getClass().getSimpleName();

    Vector<Bullet> bullets;

    Player player;

    Vector<BaseEnemy> enemies;

    public FirstScene(Context context) {
        super(context);
    }

    public FirstScene(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initVariable() {

        bullets = new Vector<>();

        player = new Player(BaseEntity.FLAG_PLAYER,1,screenWidth/2,screenHeight/2,screenWidth,screenHeight,
                BitmapManager.getInstance().getPlayer(), Const.PLAYER_SPEED);

        player.setOnActionListener(new Player.OnActionListener() {
            @Override
            public void onFire() {

                bullets.add(new Bullet(
                                BaseEntity.FLAG_PLAYER,
                                1,
                                player.getPosX()+player.getSelfWidth()/2-20,
                                player.getPosY()+player.getSelfHeight()/2-20,
                                screenWidth,screenHeight,
                                BitmapManager.getInstance().getBullet().get("player"),
                                Const.PLAYER_BULLET_SPEED,
                                player.getStatus()
                        )
                );

            }
        });

        enemies = new Vector<>();
        final SimpleEnemy simpleEnemy = new SimpleEnemy(BaseEntity.FLAG_ENEMY,1,screenWidth/2,100,screenWidth,screenHeight,Const.SIMPLE_ENEMY_SPEED,
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

        final FastEnemy fastEnemy = new FastEnemy(BaseEntity.FLAG_ENEMY,1,100,200,screenWidth,screenHeight,
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

        final LargeEnemy largeEnemy = new LargeEnemy(BaseEntity.FLAG_ENEMY,4,200,100,screenWidth,screenHeight,
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

    @Override
    protected void doDraw(Canvas canvas) {

        canvas.drawColor(Color.WHITE);

        player.onDraw(canvas);

        for(int i = 0;i< bullets.size();i++){
            bullets.get(i).onDraw(canvas);
        }

        for (int i = 0;i<enemies.size();i++){
            enemies.get(i).onDraw(canvas);
        }


    }

    @Override
    protected void doLogic() {

        player.onLogic();

        for (int i = 0;i<enemies.size();i++){
            enemies.get(i).onLogic();
        }

        for (int i = 0;i<bullets.size();i++){
            bullets.get(i).onLogic();

            bullets.get(i).checkCollision(player);

            for(int j=0;j<enemies.size();j++){

                bullets.get(i).checkCollision(enemies.get(j));

            }

        }

        for (int i = 0;i<bullets.size();i++){
            if (bullets.get(i).isDead()){
                bullets.remove(i);
                SoundManager.getInstance().play("hit");
            }
        }

        for (int i = 0;i<enemies.size();i++){
            if (enemies.get(i).isDead()){
                enemies.remove(i);
                SoundManager.getInstance().play("blast");
            }
        }




    }


    @Override
    public void start() {
        setStatus(START);
    }

    @Override
    public void pause() {
        setStatus(PAUSE);
    }

    @Override
    public void changePlayerStatus(String status) {
        if(player!=null&&!player.isDead()){
            player.setStatus(status);
        }
    }

    @Override
    public void changePlayerMoving(boolean isMoving) {
        if(player!=null&&!player.isDead()){
            player.setMoving(isMoving);
        }

    }

    @Override
    public void fire() {

        if(player!=null&&!player.isDead()){
            player.fire();
            SoundManager.getInstance().play("fire");
        }


    }
}
