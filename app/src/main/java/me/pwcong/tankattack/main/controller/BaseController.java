package me.pwcong.tankattack.main.controller;

import me.pwcong.tankattack.main.view.BaseView;

/**
 * Created by Pwcong on 2016/11/30.
 */

public interface BaseController {

    interface FirstScene{

        void setView(BaseView.MainActivityView view);

        void play();
        void pause();

        void changePlayerStatus(String status);
        void changePlayerMoving(boolean isMoving);

        void fire();

    }

}
