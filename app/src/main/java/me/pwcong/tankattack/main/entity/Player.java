package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Map;

/**
 * Created by Pwcong on 2016/11/30.
 */

public class Player extends BaseEntity implements BaseEntity.Behavior{

    private Map<String,Bitmap> player;
    private float speed;

    private OnActionListener onActionListener;

    Paint paint;
    String status;
    boolean isMoving;

    public Player(int flag, int life, float posX, float posY, float screenWidth, float screenHeight, Map<String, Bitmap> player, float speed) {
        super(flag,life, posX, posY,screenWidth,screenHeight);
        this.player = player;
        this.speed = speed;

        initVariable();

    }

    @Override
    protected void initVariable() {

        setSelfWidth(player.get(STATUS_UP).getWidth());
        setSelfHeight(player.get(STATUS_UP).getHeight());

        setPosX(getPosX()-getSelfWidth()/2);
        setPosY(getPosY()-getSelfHeight()/2);


        paint = new Paint();
        paint.setAntiAlias(true);

        status = STATUS_UP;
        isMoving = false;
    }

    @Override
    public void onDraw(Canvas canvas) {

        if(!isDead()){

            switch (status){

                case STATUS_LEFT:
                    canvas.drawBitmap(player.get(STATUS_LEFT),getPosX(),getPosY(),paint);
                    break;
                case STATUS_RIGHT:
                    canvas.drawBitmap(player.get(STATUS_RIGHT),getPosX(),getPosY(),paint);
                    break;
                case STATUS_UP:
                    canvas.drawBitmap(player.get(STATUS_UP),getPosX(),getPosY(),paint);
                    break;
                case STATUS_DOWN:
                    canvas.drawBitmap(player.get(STATUS_DOWN),getPosX(),getPosY(),paint);
                    break;
                default:break;

            }

        }

    }

    @Override
    public void onLogic() {
        autoMove();
        autoCheckLife();
    }

    private void autoMove(){

        if(isMoving){

            switch (status){
                case STATUS_LEFT:

                    if(getPosX()-speed>0)
                        setPosX(getPosX()-speed);

                    break;
                case STATUS_RIGHT:

                    if(getPosX()+speed+getSelfWidth()<getScreenWidth())
                        setPosX(getPosX()+speed);
                    break;
                case STATUS_UP:

                    if(getPosY()-speed>0)
                        setPosY(getPosY()-speed);
                    break;
                case STATUS_DOWN:

                    if(getPosY()+speed+getSelfHeight()<getScreenHeight())
                        setPosY(getPosY()+speed);

                    break;
                default:break;

            }

        }

    }

    private void autoCheckLife(){

        if(getLife()<=0)
            setDead(true);

    }

    public void checkCollision(BaseEntity other){

        if(other.getFlag() == BaseEntity.FLAG_OBJECT){

            if(Math.abs((getPosX()+getSelfWidth()/2) - (other.getPosX() + other.getSelfWidth()/2))
                    < (getSelfWidth()/2 + other.getSelfWidth()/2) &&
                    Math.abs((getPosY()+getSelfHeight()/2) - (other.getPosY() + other.getSelfHeight()/2))
                    < (getSelfHeight()/2 + other.getSelfHeight()/2)){

                ((BaseObject)other).doEvent();

                other.setDead(true);

            }
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        this.onActionListener = onActionListener;
    }

    public void fire(){

        if(onActionListener!=null)
            onActionListener.onFire();

    }

    public interface OnActionListener{

        void onFire();

    }


}
