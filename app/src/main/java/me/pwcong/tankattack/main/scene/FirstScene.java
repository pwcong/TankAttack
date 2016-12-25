package me.pwcong.tankattack.main.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import java.util.Vector;

import me.pwcong.tankattack.config.Const;
import me.pwcong.tankattack.main.controller.BaseController;
import me.pwcong.tankattack.main.entity.BaseEnemy;
import me.pwcong.tankattack.main.entity.BaseEntity;
import me.pwcong.tankattack.main.entity.BaseObject;
import me.pwcong.tankattack.main.entity.Boom;
import me.pwcong.tankattack.main.entity.Bullet;
import me.pwcong.tankattack.main.entity.Player;
import me.pwcong.tankattack.main.util.EnemyCreater;
import me.pwcong.tankattack.main.util.ObjectCreater;
import me.pwcong.tankattack.main.view.BaseView;
import me.pwcong.tankattack.manager.BitmapManager;
import me.pwcong.tankattack.manager.SharedPreferenceManager;
import me.pwcong.tankattack.manager.SoundManager;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class FirstScene extends BaseScene<BaseView.MainActivityView> implements BaseController.FirstScene{


    int maxEnemy = 3;
    int maxObject = 2;

    Vector<BaseEnemy> enemies;
    Vector<Bullet> bullets;
    Player player;
    Vector<Boom> booms;
    Vector<BaseObject> objects;

    int enemyCounts;

    public FirstScene(Context context) {
        super(context);
    }

    public FirstScene(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initVariable() {

        enemyCounts=0;

        enemies = new Vector<>();
        bullets = new Vector<>();
        objects = new Vector<>();

        player = new Player(BaseEntity.FLAG_PLAYER,3,screenWidth/2,screenHeight/2,screenWidth,screenHeight,
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

        canvas.drawColor(0xff834B20);


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

        for (int i = 0;i<objects.size();i++){
            objects.get(i).onDraw(canvas);
        }


    }

    @Override
    protected void doLogic() {

        getView().setTips(String.valueOf(currentTime/Const.FPS),String.valueOf(enemyCounts),
                String.valueOf(player.getLife()));

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

        for(int i=0;i<objects.size();i++){
            objects.get(i).onLogic();
            player.checkCollision(objects.get(i));
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

                enemyCounts++;
                enemies.remove(i);
                SoundManager.getInstance().play("blast");

            }
        }

        for(int i=0;i<objects.size();i++){
            if (objects.get(i).isDead())
                objects.remove(i);
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

            int t = SharedPreferenceManager.getInstance().getInt(Const.KEY_LIFE_TIMES,0);
            if (t<lifetime){
                SharedPreferenceManager.getInstance().putInt(Const.KEY_LIFE_TIMES,(int)lifetime);
            }

            int c = SharedPreferenceManager.getInstance().getInt(Const.KEY_ENEMY_COUNTS,0);
            if(c<enemyCounts){
                SharedPreferenceManager.getInstance().putInt(Const.KEY_ENEMY_COUNTS,enemyCounts);
            }

            getView().setSecondText(String.valueOf(lifetime),String.valueOf(enemyCounts));
            getView().showLose();


        }


        /**
         *  自动生成敌人
         */
        if(currentTime%40==0&&enemies.size()<maxEnemy){

            int random = Math.round((float)(Math.random()*2));

            switch (random){

                case 0:
                    EnemyCreater.createSimpleEnemy(screenWidth,screenHeight,enemies,bullets);
                    break;
                case 1:
                    EnemyCreater.createFastEnemy(screenWidth,screenHeight,enemies,bullets);
                    break;
                case 2:
                    EnemyCreater.createLargeEnemy(screenWidth,screenHeight,enemies,bullets);
                    break;
                default:break;

            }

        }



        if(currentTime%400==0){
            if(maxEnemy<20)
                maxEnemy++;

            if(objects.size()<maxObject){
                ObjectCreater.createBmonObject(screenWidth,screenHeight,objects,enemies);
            }


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
