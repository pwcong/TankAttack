package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;


/**
 * Created by Pwcong on 2016/11/30.
 */

public class Bullet extends BaseEntity implements BaseEntity.Behavior {

    private final String TAG =getClass().getSimpleName();


    private Bitmap bullet;
    private float speed;
    private String status;

    Vector2 direction;

    Paint paint;

    public Bullet(int flag, float posX, float posY, float screenWidth, float screenHeight, Bitmap bullet, float speed, String status) {
        super(flag, posX, posY, screenWidth, screenHeight);
        this.bullet = bullet;
        this.speed = speed;
        this.status = status;

        initVariable();
    }

    @Override
    protected void initVariable() {

        setSelfWidth(bullet.getWidth());
        setSelfHeight(bullet.getHeight());

        switch (status){

            case BaseEntity.STATUS_UP:
                direction = Vector2.UP;
                break;
            case BaseEntity.STATUS_DOWN:
                direction = Vector2.DOWN;
                break;
            case BaseEntity.STATUS_LEFT:
                direction = Vector2.LEFT;
                break;
            case BaseEntity.STATUS_RIGHT:
                direction = Vector2.RIGHT;
                break;
            default:break;
        }

        paint = new Paint();
        paint.setAntiAlias(true);

    }

    @Override
    public void onDraw(Canvas canvas) {

        if(!isDead()){

            canvas.drawBitmap(bullet,getPosX(),getPosY(),paint);

        }

    }

    @Override
    public void onLogic() {

        if(getPosX()<0||getPosX()>getScreenWidth()||getPosY()<0||getPosY()>getScreenHeight()){
            setDead(true);
        }

        setPosX(getPosX()+speed*direction.getX());
        setPosY(getPosY()+speed*direction.getY());

    }

    public void checkCollision(BaseEntity other){



        if(getFlag() != other.getFlag()){

            if(getPosX()>other.getPosX() && getPosX()<other.getPosX()+other.getSelfWidth() &&
                    getPosY()>other.getPosY()&& getPosY()<other.getPosY()+other.getSelfHeight()){

                Log.i(TAG, "checkCollision: OK");

                setDead(true);

            }
        }

    }


}
