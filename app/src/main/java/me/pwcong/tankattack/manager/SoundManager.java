package me.pwcong.tankattack.manager;

import android.media.AudioManager;
import android.media.SoundPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import me.pwcong.tankattack.App;
import me.pwcong.tankattack.R;

/**
 * Created by Pwcong on 2016/11/30.
 */

public class SoundManager {

    private static SoundManager instance;

    private SoundPool soundPool;

    private Map<String,Integer> sounds;

    private SoundManager(){

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,1);
        sounds = new ConcurrentHashMap<>();

    }

    public static synchronized SoundManager getInstance() {
        if(instance == null)
            instance = new SoundManager();

        return instance;
    }

    public void loadSound(){

        sounds.put("add",soundPool.load(App.getInstance(), R.raw.add,1));
        sounds.put("play",soundPool.load(App.getInstance(), R.raw.start,1));
        sounds.put("fire",soundPool.load(App.getInstance(), R.raw.fire,1));
        sounds.put("hit",soundPool.load(App.getInstance(), R.raw.hit,1));
        sounds.put("blast",soundPool.load(App.getInstance(), R.raw.blast,1));

    }

    public void play(String key){

        if(sounds.containsKey(key)){
            soundPool.play(sounds.get(key),1,1,1,0,1);
        }

    }

}
