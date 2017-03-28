package com.aorise.yx.healthypro.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aorise.yx.healthypro.R;
import com.aorise.yx.healthypro.baseutils.MyActivity;
import com.aorise.yx.healthypro.baseutils.Utils;
import com.aorise.yx.healthypro.entity.SocialNews;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

public class NewsDetailActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private int mCount=0;
    private TextView mZan_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setColor(this, Color.parseColor("#23f53b"));
        setContentView(R.layout.activity_news_detail);
        int index = getIntent().getIntExtra("index", 0);
        String type = getIntent().getStringExtra("type");
        initViews(type, index);

    }

    private void initViews(String type, int index) {
        switch (type) {
            //社会焦点详情界面
            case "sociafocus":
                ImageView imageView = (ImageView) findViewById(R.id.sf_detail_image);
                TextView title = (TextView) findViewById(R.id.sf_detail_title);
                TextView content = (TextView) findViewById(R.id.sf_detail_content);
                CheckBox zan = (CheckBox) findViewById(R.id.sf_zan);
                mZan_count = (TextView) findViewById(R.id.sf_zan_count1);
                zan.setOnCheckedChangeListener(this);
                String json = Utils.getJson("socialfocus.json", this);
                Gson gson = new Gson();
                SocialNews socialNews = gson.fromJson(json, SocialNews.class);
                List<SocialNews.TngouBean> social_news = socialNews.getTngou();
                SocialNews.TngouBean tngouBean = social_news.get(index);
                String url = "http://tnfs.tngou.net/img" + tngouBean.getImg();
                Log.d("TAG", "initViews: " + url);
                Glide.with(this).load(url).asBitmap().error(R.mipmap.me_info_bgimage).into(imageView);
                title.setText(tngouBean.getTitle());
                content.setText(tngouBean.getDescription());
                mZan_count.setText(String.valueOf(tngouBean.getCount()));
                mCount = tngouBean.getCount();
//                mZan_count.setText(mCount);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Log.d("TAG", "onCheckedChanged:st "+mCount);
        if (b) {
            mCount = mCount+1;
            Toast.makeText(this, "点赞成功！", Toast.LENGTH_SHORT).show();
        } else {
            mCount = mCount-1;
            Toast.makeText(this, "取消点赞！", Toast.LENGTH_SHORT).show();
        }
        Log.d("TAG", "onCheckedChanged:end "+mCount);
        mZan_count.setText(String.valueOf(mCount));
    }
}
