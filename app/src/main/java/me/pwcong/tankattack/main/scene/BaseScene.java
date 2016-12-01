package me.pwcong.tankattack.main.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import me.pwcong.tankattack.config.Const;

/**
 * Created by Pwcong on 2016/11/29.
 */

public abstract class BaseScene extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    public final String TAG = getClass().getSimpleName();

    public static final int PLAY = 1;
    public static final int PAUSE   = -1;

    private int status;

    Context context;
    SurfaceHolder surfaceHolder;

    long currentTime;

    float screenWidth;
    float screenHeight;

    volatile boolean start = false;

    public BaseScene(Context context) {
        super(context);
        init(context);

    }

    public BaseScene(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        this.surfaceHolder = getHolder();

        this.surfaceHolder.addCallback(this);

        Log.i(TAG, "init: OK");
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        initVariable();

        setStatus(PLAY);

        currentTime = 0;

        start = true;

        new Thread(this).start();

        Log.i(TAG, "surfaceCreated: OK");

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        start = false;

    }

    @Override
    public void run() {

        while (start){

            currentTime++;

            switch (getStatus()){

                case PLAY:

                    Canvas canvas = surfaceHolder.lockCanvas();

                    try {

                        if(canvas!=null){

                            doDraw(canvas);
                            doLogic();
                        }

                        Thread.sleep(Const.FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {

                        try {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }catch (Exception ignored){

                        }

                    }


                    break;
                case PAUSE:break;
                default:break;


            }



        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        screenHeight = MeasureSpec.getSize(heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;

    }

    protected abstract void initVariable();

    protected abstract void doDraw(Canvas canvas);

    protected abstract void doLogic();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
