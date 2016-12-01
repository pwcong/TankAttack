package me.pwcong.tankattack.main.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import java.util.Vector;

import me.pwcong.tankattack.config.Const;
import me.pwcong.tankattack.main.controller.BaseController;
import me.pwcong.tankattack.main.entity.BaseEntity;
import me.pwcong.tankattack.main.entity.Boom;
import me.pwcong.tankattack.main.entity.Bullet;
import me.pwcong.tankattack.main.util.EnemyCreater;
import me.pwcong.tankattack.main.entity.Player;
import me.pwcong.tankattack.main.entity.BaseEnemy;
import me.pwcong.tankattack.main.view.BaseView;
import me.pwcong.tankattack.manager.BitmapManager;
import me.pwcong.tankattack.manager.SoundManager;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class FirstScene extends BaseScene implements BaseController.FirstScene{

    public final String TAG = getClass().getSimpleName();

    BaseView.MainActivityView view;

    Vector<BaseEnemy> enemies;
    Vector<Bullet> bullets;
    Player player;
    Vector<Boom> booms;

    public FirstScene(Context context) {
        super(context);
    }

    public FirstScene(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initVariable() {

        enemies = new Vector<>();
        bullets = new Vector<>();

        EnemyCreater.createSimpleEnemy(100,100,screenWidth,screenHeight,enemies,bullets);
        EnemyCreater.createFastEnemy(200,100,screenWidth,screenHeight,enemies,bullets);
        EnemyCreater.createLargeEnemy(300,100,screenWidth,screenHeight,enemies,bullets);

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




        booms = new Vector<>();


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

        for (int i = 0;i<booms.size();i++){
            booms.get(i).onDraw(canvas);
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

                booms.add(new Boom(BaseEntity.FLAG_OTHER,1,enemies.get(i).getPosX(),enemies.get(i).getPosY(),
                        screenWidth,screenHeight,BitmapManager.getInstance().getBoom()));

                enemies.remove(i);
                SoundManager.getInstance().play("blast");


            }
        }


        for(int i=0;i<booms.size();i++){

            booms.get(i).onLogic();

        }

        for(int i=0;i<booms.size();i++){

            if(booms.get(i).isDead())
                booms.remove(i);

        }




    }


    @Override
    public void setView(BaseView.MainActivityView view) {

        this.view = view;

    }

    @Override
    public void play() {
        setStatus(PLAY);
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
