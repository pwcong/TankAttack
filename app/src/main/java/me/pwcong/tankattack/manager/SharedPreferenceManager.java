package me.pwcong.tankattack.manager;

import android.content.Context;
import android.content.SharedPreferences;

import me.pwcong.tankattack.App;
import me.pwcong.tankattack.config.Const;

/**
 * Created by Pwcong on 2016/12/1.
 */

public class SharedPreferenceManager {

    private static SharedPreferenceManager instance;

    private SharedPreferences sharedPreferences;

    private SharedPreferenceManager(){

        sharedPreferences = App.getInstance().getSharedPreferences(Const.PRE_NAME, Context.MODE_PRIVATE);

    }

    public static synchronized SharedPreferenceManager getInstance() {

        if (instance == null)
            instance = new SharedPreferenceManager();

        return instance;
    }

    public int getInt(String key,int defaultValue){

        return sharedPreferences.getInt(key,defaultValue);

    }

    public void putInt(String key,int value){

        sharedPreferences.edit().putInt(key,value).apply();

    }

}
