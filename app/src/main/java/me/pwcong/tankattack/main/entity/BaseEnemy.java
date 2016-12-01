package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Map;

import me.pwcong.tankattack.config.Const;

/**
 * Created by Pwcong on 2016/11/30.
 */

public class BaseEnemy extends BaseEntity implements BaseEntity.Behavior{

    private Map<String,Bitmap> enemy;
    private float speed;

    private OnActionListener onActionListener;

    Paint paint;
    String status;

    int current = 0;
    int moveDuration = 100;
    int fireDuration = 30;

    public BaseEnemy(int flag, int life, float posX, float posY, float screenWidth, float screenHeight, Map<String, Bitmap> enemy, float speed) {
        super(flag, life, posX, posY,screenWidth,screenHeight);
        this.enemy = enemy;
        this.speed = speed;

        initVariable();

    }

    @Override
    protected void initVariable() {

        setSelfWidth(enemy.get(STATUS_UP).getWidth());
        setSelfHeight(enemy.get(STATUS_UP).getHeight());

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
                    canvas.drawBitmap(enemy.get(STATUS_LEFT),getPosX(),getPosY(),paint);
                    break;
                case STATUS_RIGHT:
                    canvas.drawBitmap(enemy.get(STATUS_RIGHT),getPosX(),getPosY(),paint);
                    break;
                case STATUS_UP:
                    canvas.drawBitmap(enemy.get(STATUS_UP),getPosX(),getPosY(),paint);
                    break;
                case STATUS_DOWN:
                    canvas.drawBitmap(enemy.get(STATUS_DOWN),getPosX(),getPosY(),paint);
                    break;
                default:break;

            }

        }

    }

    @Override
    public void onLogic() {
        autoMove();
        autoFire();
        autoCheckLife();
    }

    protected void autoFire(){

        if(current%fireDuration==0)
            if(Math.random()> Const.BASE_ENEMY_FIRE_SALT)
                if(onActionListener!=null)
                    onActionListener.onFire();


    }

    protected void autoMove(){

        current++;
        if (current>moveDuration){
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

    protected void autoCheckLife(){

        if(getLife()<=0)
            setDead(true);

    }

//    public void checkCollision(BaseEntity other){
//
//        if(other.getFlag() == BaseEntity.FLAG_PLAYER){
//
//            if(Math.abs(getPosX()-other.getPosX() + (getSelfWidth()+other.getSelfWidth())/2)<(getSelfWidth()+other.getSelfWidth())/2 &&
//                    Math.abs(getPosY()-other.getPosY() + (getSelfHeight()+other.getSelfHeight())/2)<(getSelfHeight()+other.getSelfHeight())/2){
//
//                if(getLife()>0)
//                    setLife(getLife()-1);
//
//                if(other.getLife()>0)
//                    other.setLife(other.getLife()-1);
//
//            }
//        }
//
//    }

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
