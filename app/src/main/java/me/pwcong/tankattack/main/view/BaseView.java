package me.pwcong.tankattack.main.view;

/**
 * Created by Pwcong on 2016/12/1.
 */

public interface BaseView {

    interface MainActivityView{

        void showMenu();
        void hideMenu();
        void showController();
        void hideController();
        void showTips();
        void hideTips();
        void showLose();
        void setSecondText(String second,String counts);
        void setTips(String lifeTimes,String enemyCounts,String playerLife);
    }
}
