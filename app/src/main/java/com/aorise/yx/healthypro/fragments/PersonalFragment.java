package com.aorise.yx.healthypro.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.activities.ChangePwdActivity;
import com.aorise.yx.healthypro.activities.Login_activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener {


    private TextView mUsername;
    private ImageView mImage;

    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_personal, container, false);
        LinearLayout me_changepwd = (LinearLayout) ret.findViewById(R.id.me_changepwd);
        LinearLayout me_exit = (LinearLayout) ret.findViewById(R.id.me_exit);
        mUsername = (TextView) ret.findViewById(R.id.me_username);
        mImage = (ImageView) ret.findViewById(R.id.me_userInfo);
        initView();
        me_changepwd.setOnClickListener(this);
        me_exit.setOnClickListener(this);
        return ret;
    }

    public void initView() {
        SharedPreferences sp = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        String usertype = sp.getString("usertype", "");
        String us = "用户名：" + usertype;
        mUsername.setText(us);
        switch (usertype) {
            case "yx123456":
                mImage.setImageResource(R.mipmap.yx123456);
                break;
            case "yx7758521":
                mImage.setImageResource(R.mipmap.yx7758521);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.me_changepwd:
                // TODO: 2017/3/29 跳转到修改界面
                Intent intent = new Intent(getActivity(), ChangePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.me_exit:
                // TODO: 2017/3/29 跳转到登录界面
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("退出登录").setIcon(android.R.drawable.ic_dialog_info).setMessage("您真的要退出吗？")
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(getActivity(), Login_activity.class);
                        getActivity().finish();
                        SharedPreferences.Editor editor = getContext().getSharedPreferences("info", Context.MODE_PRIVATE).edit();
                        editor.clear();
                        editor.apply();
                        startActivity(intent1);
                    }
                });
                builder.show();
                break;
        }
    }
}
