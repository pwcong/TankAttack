package me.pwcong.tankattack;

import android.app.Application;

/**
 * Created by Pwcong on 2016/11/30.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

    }

    public static App getInstance() {
        return instance;
    }
}
