package com.aorise.yx.healthypro.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


import com.aorise.yx.healthypro.MainActivity;
import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.baseutils.MyActivity;

import java.io.IOException;


public class Lead_activity extends MyActivity {
    private Boolean islogined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
        islogined = sp.getBoolean("islogined", false);//判断是否已经登录
        init();
        event();
    }

    private void init() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void event() {
        Handler handler = new Handler();
        handler.postDelayed(runnable, 2500);//1.5秒之后将要执行的动作

    }

    Runnable runnable = new Runnable() {//新建一个线程
        @Override
        public void run() {
            if (islogined) {
                startActivity(MainActivity.class);
                finish();
            } else {
                startActivity(Login_activity.class);
                finish();
            }
        }
    };
}
