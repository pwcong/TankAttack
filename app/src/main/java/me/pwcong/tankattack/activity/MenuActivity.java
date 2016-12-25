package me.pwcong.tankattack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import me.pwcong.tankattack.R;
import me.pwcong.tankattack.config.Const;
import me.pwcong.tankattack.manager.ActivityManager;
import me.pwcong.tankattack.manager.SharedPreferenceManager;
import rx.functions.Action1;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class MenuActivity extends BaseActivity {

    Button btn_start;
    Button btn_help;
    Button btn_top;

    private long mExitTime;

    @Override
    protected int setView() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initVariable(Bundle savedInstanceState) {

        btn_start = (Button) findViewById(R.id.btn_start);
        RxView.clicks(btn_start).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

                redirect2MainActivity();

            }
        });

        btn_help = (Button) findViewById(R.id.btn_help);
        RxView.clicks(btn_help).throttleFirst(1000,TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

                redirect2AboutActivity();
            }
        });

        btn_top = (Button) findViewById(R.id.btn_top);
        RxView.clicks(btn_top).throttleFirst(1000,TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

                int lifeTimes = SharedPreferenceManager.getInstance().getInt(Const.KEY_LIFE_TIMES,0);
                int enemyCounts = SharedPreferenceManager.getInstance().getInt(Const.KEY_ENEMY_COUNTS,0);
                String text = "最高存活时长为：" + String.valueOf(lifeTimes) + " s ，最高消灭敌人数为："
                        + String.valueOf(enemyCounts) + " 。";

                Snackbar.make(btn_top, text,Snackbar.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void doAction() {

    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {

            Snackbar.make(btn_top,"再按一次退出",Snackbar.LENGTH_SHORT).show();

            mExitTime = System.currentTimeMillis();

        } else {
            ActivityManager.getInstance().removeAll();
        }
    }

    private void redirect2MainActivity(){

        startActivity(new Intent(MenuActivity.this,MainActivity.class));
        finish();
    }

    private void redirect2AboutActivity(){

        startActivity(new Intent(MenuActivity.this,AboutActivity.class));

    }


}
