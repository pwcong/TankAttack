package me.pwcong.tankattack.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import me.pwcong.tankattack.manager.ActivityManager;

/**
 * Created by Pwcong on 2016/11/29.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.getInstance().register(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(setView());

        initVariable(savedInstanceState);

        doAction();

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ActivityManager.getInstance().setCurrentActivity(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityManager.getInstance().unregister(this);


    }

    protected abstract int setView();

    protected abstract void initVariable(Bundle savedInstanceState);

    protected abstract void doAction();


}
