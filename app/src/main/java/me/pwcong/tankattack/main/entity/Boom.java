package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.List;

/**
 * Created by Pwcong on 2016/12/1.
 */

public class Boom extends BaseEntity implements BaseEntity.Behavior {

    private List<Bitmap> boom;

    Paint paint;
    int index;
    int current;
    int duraction;


    public Boom(int flag, int life, float posX, float posY, float screenWidth, float screenHeight, List<Bitmap> boom) {
        super(flag, life, posX, posY, screenWidth, screenHeight);
        this.boom = boom;
    }

    @Override
    protected void initVariable() {

        paint = new Paint();
        paint.setAntiAlias(true);

        index = 0;
        current = 0;
        duraction = 4;
    }

    @Override
    public void onDraw(Canvas canvas) {

        if(!isDead()){

            canvas.drawBitmap(boom.get(index),getPosX(),getPosY(),paint);

        }

    }

    @Override
    public void onLogic() {

        current++;
        if(current>duraction){

            current=0;
            if(index+1<boom.size()){
                index++;
            }else {
                setDead(true);
            }

        }

    }
}
