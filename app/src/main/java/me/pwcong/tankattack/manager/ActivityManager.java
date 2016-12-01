package me.pwcong.tankattack.manager;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class ActivityManager {

    private static ActivityManager instance;

    private Vector<Activity> activities;

    private Activity currentActivity;

    private ActivityManager(){
        activities = new Vector<>();
    }

    public synchronized static ActivityManager getInstance() {

        if(instance == null)
            instance = new ActivityManager();

        return instance;
    }

    public void register(Activity activity){
        if(!activities.contains(activity))
            activities.add(activity);
    }

    public void unregister(Activity activity){
        if (activities.contains(activity))
            activities.add(activity);
    }

    public void removeAll(){

        for (Activity activity:activities)
            activity.finish();

        activities.clear();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        },1500);

    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
