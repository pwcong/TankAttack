package me.pwcong.tankattack.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import me.pwcong.tankattack.R;
import me.pwcong.tankattack.manager.BitmapManager;
import me.pwcong.tankattack.manager.SoundManager;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected int setView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariable(Bundle savedInstanceState) {

    }

    @Override
    protected void doAction() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                BitmapManager.getInstance().loadBitmap();
                SoundManager.getInstance().loadSound();

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                redirect2MenuActivity();
                            }
                        });
                    }
                },1000);


            }
        }.execute();

    }

    private void redirect2MenuActivity(){

        startActivity(new Intent(SplashActivity.this,MenuActivity.class));
        finish();

    }

}
