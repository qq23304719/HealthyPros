package com.aorise.yx.healthypro.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aorise.yx.healthypro.MainActivity;
import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.baseutils.MyActivity;

/**
 * Created by Administrator on 2016/12/23.
 */

public class Login_activity extends MyActivity {
    private EditText username, password;
    private TextView denglu, wangji;
    public static Login_activity instance = null;
    private final static String TAG = "6666";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.login);
        instance = this;
        init();
        event();
    }

    private void init() {
        this.username = (EditText) this.findViewById(R.id.username);
        this.password = (EditText) this.findViewById(R.id.password);
        this.denglu = (TextView) this.findViewById(R.id.denglu);
        this.wangji = (TextView) this.findViewById(R.id.wangji);
    }

    private void event() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        denglu.setOnClickListener(listener);
        wangji.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.denglu:
                    String username = Login_activity.this.username.getText().toString();
                    String password = Login_activity.this.password.getText().toString();
                    SharedPreferences password_ = getSharedPreferences("password", MODE_PRIVATE);
                    SharedPreferences password_1 = getSharedPreferences("password1", MODE_PRIVATE);
                    if (username.equals("yx123456") && password.equals("123456")
                            || username.equals("yx7758521") && password.equals("123456")
                            ||username.equals("yx123456")&&password.equals(password_.getString("password","123456"))
                            ||username.equals("yx7758521")&&password.equals(password_1.getString("password1","123456"))){
                        Intent intent = new Intent(Login_activity.this, MainActivity.class);
                        startActivity(intent);
                        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putBoolean("islogined", true);
                        edit.putString("usertype", username);
                        edit.apply();
                        Toast.makeText(Login_activity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Login_activity.this, "用户名或者密码不正确！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.wangji:

                    break;
            }
        }
    };


}
