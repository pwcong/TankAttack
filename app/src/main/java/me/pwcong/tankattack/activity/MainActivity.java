package me.pwcong.tankattack.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import me.pwcong.tankattack.R;
import me.pwcong.tankattack.main.controller.BaseController;
import me.pwcong.tankattack.main.entity.BaseEntity;
import me.pwcong.tankattack.main.scene.BaseScene;
import me.pwcong.tankattack.manager.SoundManager;
import rx.functions.Action1;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class MainActivity extends BaseActivity implements View.OnTouchListener {

    private final String TAG = getClass().getSimpleName();

    BaseController.FirstScene scene;

    Button btn_up;
    Button btn_down;
    Button btn_right;
    Button btn_left;
    Button btn_fire;

    @Override
    protected int setView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable(Bundle savedInstanceState) {

        scene = (BaseController.FirstScene) findViewById(R.id.scene);
        initButton();
    }

    private void initButton(){

        btn_up = (Button) findViewById(R.id.btn_up);
        btn_up.setOnTouchListener(this);

        btn_down = (Button) findViewById(R.id.btn_down);
        btn_down.setOnTouchListener(this);

        btn_left = (Button) findViewById(R.id.btn_left);
        btn_left.setOnTouchListener(this);

        btn_right = (Button) findViewById(R.id.btn_right);
        btn_right.setOnTouchListener(this);

        btn_fire = (Button) findViewById(R.id.btn_fire);
        RxView.clicks(btn_fire).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                scene.fire();
            }
        });



    }

    @Override
    protected void doAction() {

        SoundManager.getInstance().play("start");

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()){

            case MotionEvent.ACTION_DOWN:

                switch (view.getId()){

                    case R.id.btn_up:
                        scene.changePlayerStatus(BaseEntity.STATUS_UP);
                        scene.changePlayerMoving(true);
                        break;
                    case R.id.btn_down:
                        scene.changePlayerStatus(BaseEntity.STATUS_DOWN);
                        scene.changePlayerMoving(true);
                        break;
                    case R.id.btn_left:
                        scene.changePlayerStatus(BaseEntity.STATUS_LEFT);
                        scene.changePlayerMoving(true);
                        break;
                    case R.id.btn_right:
                        scene.changePlayerStatus(BaseEntity.STATUS_RIGHT);
                        scene.changePlayerMoving(true);
                        break;
                }

                break;
            case MotionEvent.ACTION_UP:
                scene.changePlayerMoving(false);
                break;
            default:break;

        }

        return true;
    }

}
