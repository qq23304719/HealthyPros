package com.aorise.yx.healthypro.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.baseutils.MyActivity;

public class ChangePwdActivity extends MyActivity implements View.OnClickListener {

    private String mOld;
    private String mNew_;
    private String mNew_1;
    private EditText mPwd_old;
    private EditText mPwd_new;
    private EditText mPwd_new1;
    private SharedPreferences mSp;
    private Button mSumbit_change;
    private Toolbar mPwd_toolbar;
    private ImageView mBtn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        mSumbit_change = (Button) findViewById(R.id.sumbit_change);
        mSumbit_change.setOnClickListener(this);
        mPwd_old = (EditText) findViewById(R.id.pwd_old);
        mPwd_new = (EditText) findViewById(R.id.pwd_new);
        mPwd_new1 = (EditText) findViewById(R.id.pwd_new1);
        mPwd_toolbar = (Toolbar) findViewById(R.id.pwd_toolbar);
        mBtn_back = (ImageView) findViewById(R.id.btn_back);
        initview();
    }

    private void initview() {
        setColor(this, Color.parseColor("#35d085"));
        mBtn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sumbit_change:
                mOld = mPwd_old.getText().toString();
                mNew_ = mPwd_new.getText().toString();
                mNew_1 = mPwd_new1.getText().toString();
                if (mOld.length() > 5 && mNew_.length() > 5 && mNew_1.length() > 5) {
                    if (mNew_.equals(mNew_1)) {
                        SharedPreferences info = ChangePwdActivity.this.getSharedPreferences("info", MODE_PRIVATE);
                        SharedPreferences sp = ChangePwdActivity.this.getSharedPreferences("password", MODE_PRIVATE);
                        SharedPreferences sp1 = ChangePwdActivity.this.getSharedPreferences("password1", MODE_PRIVATE);
                        String usertype = info.getString("usertype", "");
                        if (usertype.equals("yx123456")){
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("password", mNew_);
                            editor.apply();
                        }else {
                            SharedPreferences.Editor editor1 = sp1.edit();
                            editor1.putString("password1", mNew_);
                            editor1.apply();
                        }
                        Intent intent = new Intent(ChangePwdActivity.this, Login_activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "密码的位数应为不少于6位，不大于20位", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
