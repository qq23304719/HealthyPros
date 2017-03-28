package com.aorise.yx.healthypro.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.entity.News;
import com.aorise.yx.healthypro.entity.SocialNews;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Yx on 10:43 2017/3/17 03.
 */

public class SocialFousAdapter extends BaseAdapter {
    private List<News.TngouBean> mList;
    private Context mContext;

    public SocialFousAdapter(List<News.TngouBean> list, Context context) {
        mList = list;
        mContext = context;
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
        View ret ;
        if (view != null) {
            ret = view;
        } else {
            ret = LayoutInflater.from(mContext).inflate(R.layout.socialfocus_item, viewGroup, false);
        }
        MyViewHolder myViewHolder = (MyViewHolder) ret.getTag();
        if (myViewHolder == null) {
            myViewHolder=new MyViewHolder(ret);
            ret.setTag(myViewHolder);
        }
        myViewHolder.bindview(mList,i);
        return ret;
    }

    public static class MyViewHolder {
        ImageView mImageView;
        TextView mTitle;
        TextView mKeyword;

        public MyViewHolder(View view) {
            mImageView = (ImageView) view.findViewById(R.id.socialfocus_image);
            mTitle = (TextView) view.findViewById(R.id.social_title);
            mKeyword=(TextView) view.findViewById(R.id.social_keywords);
        }

        public void bindview(List<News.TngouBean> list,int position) {
            News.TngouBean bean = list.get(position);
            mTitle.setText(bean.getTitle());
            mKeyword.setText(bean.getKeywords());
            String url="http://tnfs.tngou.net/img"+bean.getImg();
            Glide.with(mImageView.getContext()).load(url).asBitmap().into(mImageView);
        }
    }
}
