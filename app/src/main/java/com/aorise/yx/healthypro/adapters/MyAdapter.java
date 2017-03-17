package com.aorise.yx.healthypro.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Yx on 9:59 2017/3/17 03.
 */

public class MyAdapter extends BaseAdapter {
    private List mList;
    private Context mContext;

    public MyAdapter(Context context, List list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mList != null) {
            ret = mList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int i) {
        Object ret = null;
        if (mList != null) {
            ret = mList.get(i);
        }
        return ret;
    }

    @Override
    public long getItemId(int i) {
        int ret = 0;
        if (mList != null) {
            ret = i;
        }
        return ret;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
