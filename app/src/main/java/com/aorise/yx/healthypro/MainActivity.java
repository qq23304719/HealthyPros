package com.aorise.yx.healthypro;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.os.SystemClock;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aorise.yx.healthypro.baseutils.MyActivity;
import com.aorise.yx.healthypro.fragments.NewsFragment;
import com.aorise.yx.healthypro.fragments.PersonalFragment;
import com.aorise.yx.healthypro.fragments.VideoFragment;

public class MainActivity extends MyActivity implements RadioGroup.OnCheckedChangeListener {

    private NewsFragment mNewsFragment;
    private VideoFragment mVideoFragment;
    private PersonalFragment mPersonalFragment;
    private long currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setColor(this, Color.parseColor("#35D085"));
        mNewsFragment = new NewsFragment();
        mVideoFragment = new VideoFragment();
        mPersonalFragment = new PersonalFragment();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
        radioGroup.setOnCheckedChangeListener(this);
        RadioButton sportButton = (RadioButton) findViewById(R.id.group_news);
        sportButton.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        switch (i) {
            case R.id.group_news:
                tx.replace(R.id.fragments_container, mNewsFragment);
                break;
            case R.id.group_video:
                tx.replace(R.id.fragments_container, mVideoFragment);
                break;
            case R.id.group_personal:
                tx.replace(R.id.fragments_container, mPersonalFragment);
                break;
        }
        tx.commit();
    }

    @Override
    public void onBackPressed() {
        long time = SystemClock.uptimeMillis();
        if (time - currentTime < 3000) {
            finish();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            currentTime = SystemClock.uptimeMillis();
        }
    }
}
