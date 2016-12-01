package me.pwcong.tankattack.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import me.pwcong.tankattack.R;
import me.pwcong.tankattack.main.controller.BaseController;
import me.pwcong.tankattack.main.entity.BaseEntity;
import me.pwcong.tankattack.main.view.BaseView;
import me.pwcong.tankattack.manager.ActivityManager;
import me.pwcong.tankattack.manager.SoundManager;
import rx.functions.Action1;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class MainActivity extends BaseActivity implements View.OnTouchListener,BaseView.MainActivityView {

    private final String TAG = getClass().getSimpleName();

    BaseController.FirstScene scene;

    RelativeLayout rootController;
    Button btn_up;
    Button btn_down;
    Button btn_right;
    Button btn_left;
    Button btn_fire;

    RelativeLayout rootMenu;
    Button btn_back;
    Button btn_back2Menu;
    Button btn_exit;

    RelativeLayout rootLose;
    Button btn_back2menu_lose;
    Button btn_exit_lose;
    Button btn_again;
    TextView text_second;


    @Override
    protected int setView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable(Bundle savedInstanceState) {

        scene = (BaseController.FirstScene) findViewById(R.id.scene);
        initController();
        initMenu();
        initLose();
    }

    private void initController(){

        rootController = (RelativeLayout) findViewById(R.id.root_controller);

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

    private void initMenu(){

        rootMenu = (RelativeLayout) findViewById(R.id.root_menu);

        btn_back = (Button) findViewById(R.id.btn_back);
        RxView.clicks(btn_back).throttleFirst(1000,TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

                scene.play();
                hideMenu();
                showController();

            }
        });


        btn_back2Menu = (Button) findViewById(R.id.btn_back2menu);
        RxView.clicks(btn_back2Menu).throttleFirst(1000,TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {


            }
        });

        btn_exit = (Button) findViewById(R.id.btn_exit);
        RxView.clicks(btn_exit).throttleFirst(1000,TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                ActivityManager.getInstance().removeAll();
            }
        });
    }

    private void initLose(){

        rootLose = (RelativeLayout) findViewById(R.id.root_lose);
        btn_back2menu_lose = (Button) findViewById(R.id.btn_back2menu_lose);
        RxView.clicks(btn_back2menu_lose).throttleFirst(1000,TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {


            }
        });

        btn_exit_lose = (Button) findViewById(R.id.btn_exit_lose);
        RxView.clicks(btn_exit_lose).throttleFirst(1000,TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {


            }
        });


        btn_again = (Button) findViewById(R.id.btn_again);
        RxView.clicks(btn_again).throttleFirst(1000,TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {



            }
        });


        text_second = (TextView) findViewById(R.id.text_second);



    }

    @Override
    protected void doAction() {

        SoundManager.getInstance().play("play");

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

    @Override
    public void showMenu() {

        rootMenu.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideMenu() {

        rootMenu.setVisibility(View.GONE);


    }

    @Override
    public void showController() {

        rootController.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideController() {

        rootController.setVisibility(View.GONE);

    }

    @Override
    public void showLose() {

        rootLose.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLose() {

        rootLose.setVisibility(View.GONE);

    }

    @Override
    public void setSecondText(String text) {

        text_second.setText(text);

    }

    @Override
    public void onBackPressed() {

        scene.pause();
        showMenu();
        hideController();

    }
}
