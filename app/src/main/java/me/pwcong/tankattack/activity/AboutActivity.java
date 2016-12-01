package me.pwcong.tankattack.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.pwcong.tankattack.R;

/**
 * Created by Pwcong on 2016/11/29.
 */

public class AboutActivity extends BaseActivity {

    Toolbar mToolbar;

    @Override
    protected int setView() {
        return R.layout.activity_about;
    }

    @Override
    protected void initVariable(Bundle savedInstanceState) {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("坦克世界");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void doAction() {

    }
}
