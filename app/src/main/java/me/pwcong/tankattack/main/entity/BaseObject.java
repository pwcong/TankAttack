package me.pwcong.tankattack.main.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Pwcong on 2016/12/25.
 */

public abstract class BaseObject extends BaseEntity implements BaseEntity.Behavior {

    Bitmap bitmap;

    Paint paint;

    OnEventListener onEventListener;


    public BaseObject(int flag, int life, float posX, float posY, float screenWidth, float screenHeight,Bitmap bitmap) {
        super(flag, life, posX, posY, screenWidth, screenHeight);

        this.bitmap = bitmap;
    }

    @Override
    protected void initVariable() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {

        if(!isDead() && bitmap!=null){
            canvas.drawBitmap(bitmap,getPosX(),getPosY(),paint);
        }

    }

    @Override
    public void onLogic() {

    }

    public void setOnEventListener(OnEventListener onEventListener) {
        this.onEventListener = onEventListener;
    }

    public void doEvent(){
        if (onEventListener!=null)
            onEventListener.doEvent();
    }

    public interface OnEventListener{
        void doEvent();
    }



}
