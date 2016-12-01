package me.pwcong.tankattack.main.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import java.util.Vector;

import me.pwcong.tankattack.config.Const;
import me.pwcong.tankattack.main.controller.BaseController;
import me.pwcong.tankattack.main.entity.BaseEntity;
import me.pwcong.tankattack.main.entity.Boom;
import me.pwcong.tankattack.main.entity.Bullet;
import me.pwcong.tankattack.main.param.Vector2;
import me.pwcong.tankattack.main.util.EnemyCreater;
import me.pwcong.tankattack.main.entity.Player;
import me.pwcong.tankattack.main.entity.BaseEnemy;
import me.pwcong.tankattack.main.view.BaseView;
import me.pwcong.tankattack.manager.BitmapManager;
import me.pwcong.tankattack.manager.SharedPreferenceManager;
import me.pwcong.tankattack.manager.SoundManager;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class FirstScene extends BaseScene<BaseView.MainActivityView> implements BaseController.FirstScene{

    public final String TAG = getClass().getSimpleName();

    int maxEnemy = 3;

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

        SoundManager.getInstance().play("add");


        booms = new Vector<>();


    }

    @Override
    protected void doDraw(Canvas canvas) {

        canvas.drawColor(Color.LTGRAY);

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

        if(player.isDead()){

            booms.add(new Boom(BaseEntity.FLAG_OTHER,1,player.getPosX(),player.getPosY(),
                    screenWidth,screenHeight,BitmapManager.getInstance().getBoom()));

            SoundManager.getInstance().play("blast");

            setStatus(PAUSE);

            long lifetime = currentTime/Const.FPS;

            int t = SharedPreferenceManager.getInstance().getInt(Const.KEY_SCORE,0);
            if (t<lifetime){
                SharedPreferenceManager.getInstance().putInt(Const.KEY_SCORE,(int)lifetime);
            }

            getView().setSecondText(String.valueOf(lifetime));
            getView().showLose();


        }


        if(currentTime%40==0&&enemies.size()<maxEnemy){

            int random = Math.round((float)(Math.random()*2));

            Vector2 pos = new Vector2((float)((screenWidth-100)*Math.random()+50),(float)((screenHeight-100)*Math.random()+50));

            switch (random){

                case 0:
                    EnemyCreater.createSimpleEnemy(pos.getX(),pos.getY(),screenWidth,screenHeight,enemies,bullets);
                    break;
                case 1:
                    EnemyCreater.createFastEnemy(pos.getX(),pos.getY(),screenWidth,screenHeight,enemies,bullets);
                    break;
                case 2:
                    EnemyCreater.createLargeEnemy(pos.getX(),pos.getY(),screenWidth,screenHeight,enemies,bullets);
                    break;
                default:break;

            }

        }

        if(currentTime%400==0){
            if(maxEnemy<20)
                maxEnemy++;
        }




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
