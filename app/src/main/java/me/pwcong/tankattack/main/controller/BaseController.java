package me.pwcong.tankattack.main.controller;

/**
 * Created by Pwcong on 2016/11/30.
 */

public interface BaseController {

    interface FirstScene{

        void play();
        void pause();

        void changePlayerStatus(String status);
        void changePlayerMoving(boolean isMoving);

        void fire();

    }

}
