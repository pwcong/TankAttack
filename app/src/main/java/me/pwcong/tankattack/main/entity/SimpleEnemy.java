package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Map;
import java.util.Random;

import me.pwcong.tankattack.config.Const;

/**
 * Created by Pwcong on 2016/11/30.
 */

public class SimpleEnemy extends BaseEntity implements BaseEntity.Behavior{

    private Map<String,Bitmap> simpleEnemy;
    private float speed;

    private OnActionListener onActionListener;

    Paint paint;
    String status;

    int current = 0;
    int duration = 100;

    public SimpleEnemy(int flag, float posX, float posY, float screenWidth, float screenHeight, Map<String, Bitmap> simpleEnemy, float speed) {
        super(flag, posX, posY,screenWidth,screenHeight);
        this.simpleEnemy = simpleEnemy;
        this.speed = speed;

        initVariable();

    }

    @Override
    protected void initVariable() {

        setSelfWidth(simpleEnemy.get(STATUS_UP).getWidth());
        setSelfHeight(simpleEnemy.get(STATUS_UP).getHeight());

        setPosX(getPosX()-getSelfWidth()/2);
        setPosY(getPosY()-getSelfHeight()/2);

        paint = new Paint();
        paint.setAntiAlias(true);

        status = getRandomStatus();
    }

    @Override
    public void onDraw(Canvas canvas) {

        if(!isDead()){

            switch (status){

                case STATUS_LEFT:
                    canvas.drawBitmap(simpleEnemy.get(STATUS_LEFT),getPosX(),getPosY(),paint);
                    break;
                case STATUS_RIGHT:
                    canvas.drawBitmap(simpleEnemy.get(STATUS_RIGHT),getPosX(),getPosY(),paint);
                    break;
                case STATUS_UP:
                    canvas.drawBitmap(simpleEnemy.get(STATUS_UP),getPosX(),getPosY(),paint);
                    break;
                case STATUS_DOWN:
                    canvas.drawBitmap(simpleEnemy.get(STATUS_DOWN),getPosX(),getPosY(),paint);
                    break;
                default:break;

            }

        }

    }

    @Override
    public void onLogic() {
        autoMove();
        autoFire();
    }

    private void autoFire(){

        if(current%30==0)
            if(Math.random()> Const.SIMPLE_ENEMY_FIRE_SALT)
                if(onActionListener!=null)
                    onActionListener.onFire();


    }

    private void autoMove(){

        current++;
        if (current>duration){
            setStatus(getRandomStatus());
            current = 0;
        }

        switch (status){
            case STATUS_LEFT:

                if(getPosX()-speed>0)
                    setPosX(getPosX()-speed);
                else
                    setStatus(STATUS_RIGHT);

                break;
            case STATUS_RIGHT:

                if(getPosX()+speed+getSelfWidth()<getScreenWidth())
                    setPosX(getPosX()+speed);
                else
                    setStatus(STATUS_LEFT);
                break;
            case STATUS_UP:

                if(getPosY()-speed>0)
                    setPosY(getPosY()-speed);
                else
                    setStatus(STATUS_DOWN);
                break;
            case STATUS_DOWN:

                if(getPosY()+speed+getSelfHeight()<getScreenHeight())
                    setPosY(getPosY()+speed);
                else
                    setStatus(STATUS_UP);

                break;
            default:break;

        }


    }

    public void checkCollision(BaseEntity other){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        this.onActionListener = onActionListener;
    }

    public interface OnActionListener{

        void onFire();

    }




}
