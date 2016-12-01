package me.pwcong.tankattack.main.entity;

import java.util.Vector;

/**
 * Created by Pwcong on 2016/12/1.
 */

public class EnemyCreater extends Thread{

    private Vector<BaseEntity> entities;
    private Vector<Bullet> bullets;

    private boolean start;

    public EnemyCreater(Vector<BaseEntity> entities, Vector<Bullet> bullets) {
        this.entities = entities;
        this.bullets = bullets;

        start = true;

    }

    @Override
    public void run() {
        while (start){





        }
    }

    public void stopCreate(){

        start = false;

    }
}
