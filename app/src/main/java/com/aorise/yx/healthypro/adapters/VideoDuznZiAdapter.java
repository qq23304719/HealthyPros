package com.aorise.yx.healthypro.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aorise.yx.healthypro.entity.VideoDuanZiItem;
import com.squareup.picasso.Picasso;

import com.aorise.yx.healthypro.R;

import java.io.IOException;
import java.util.List;


/**
 * Created by jichunxiao on 2016/11/10.
 */

public class VideoDuznZiAdapter extends RecyclerView.Adapter implements MediaPlayer.OnPreparedListener {
    private Context mContext;
    private List<VideoDuanZiItem> mItems;
    public static MediaPlayer sMediaPlayer;
    public static int sIsPlayingPosition;
    public static SimpleViewHolder sViewHolder;

    public VideoDuznZiAdapter(Context context, List<VideoDuanZiItem> items) {
        mContext = context;
        mItems = items;
//        TelephonyManager service = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        service.getDeviceId();
        if (sMediaPlayer == null) {
            sMediaPlayer = new MediaPlayer();
        }
        sMediaPlayer.setOnPreparedListener(this);
    }

    public void stopPlay(int position) {
        if (position > sIsPlayingPosition) {
            if (sMediaPlayer != null) {
                if (sMediaPlayer.isPlaying()) {
                    sMediaPlayer.stop();
                    sViewHolder.setViewVisble();
                }
            }
        }
    }

    public void stopPlay() {
            if (sMediaPlayer != null) {
                if (sMediaPlayer.isPlaying()) {
                    sMediaPlayer.stop();
                    sViewHolder.setViewVisble();
                }
            }
        }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder ret = null;
        View itemView;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        itemView = layoutInflater.inflate(R.layout.video_duanzi, parent, false);
        ret = new SimpleViewHolder(itemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoDuanZiItem item = mItems.get(position);
        SimpleViewHolder holder1 = (SimpleViewHolder) holder;
        holder1.bindView(item);
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mItems != null) {
            ret = mItems.size();
        }
        return ret;
    }

    public void destroy() {
        if (sMediaPlayer != null) {
            if (sMediaPlayer.isPlaying()) {
                sMediaPlayer.stop();
            }
            sMediaPlayer.reset();
            sMediaPlayer.release();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SurfaceHolder.Callback {
        private TextView mTitle;
        private ImageView background;
        private ImageView play;
        private SurfaceView mSurfaceView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }

        void bindView(VideoDuanZiItem duanZiItem) {
            FrameLayout linearLayout = (FrameLayout) itemView.findViewById(R.id.video_duanzi_layout);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(this);
                linearLayout.setTag(duanZiItem);
            }
            play = (ImageView) itemView.findViewById(R.id.video_duanzi_play);
            mTitle = (TextView) itemView.findViewById(R.id.video_duanzi_title);
            mTitle.setText(duanZiItem.getTitle());
            TextView commentsCount = (TextView) itemView.findViewById(R.id.video_duanzi_comments_count);
            commentsCount.setText(duanZiItem.getCommentsall());
            TextView playCount = (TextView) itemView.findViewById(R.id.video_duanzi_play_count);
            playCount.setText(duanZiItem.getPlayTime());
            mSurfaceView = ((SurfaceView) itemView.findViewById(R.id.video_duanzi_surface_view));
            mSurfaceView.getHolder().addCallback(this);
            background = (ImageView) itemView.findViewById(R.id.video_duanzi_background);
            Context context = background.getContext();
            Picasso.with(context)
                    .load(duanZiItem.getImage())
                    .config(Bitmap.Config.ARGB_8888)
                    .into(background);
        }

        public void setViewVisble() {
            if (play.getVisibility() == View.INVISIBLE) {
                play.setVisibility(View.VISIBLE);
            }
            if (mTitle.getVisibility() == View.INVISIBLE) {
                mTitle.setVisibility(View.VISIBLE);
            }
            if (background.getVisibility() == View.INVISIBLE) {
                background.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            Object tag = v.getTag();
            VideoDuanZiItem duanZiItem = (VideoDuanZiItem) tag;
            String video_url = duanZiItem.getVideo_url();
            if (sViewHolder != null) {
                sViewHolder.background.setVisibility(View.VISIBLE);
                sViewHolder.play.setVisibility(View.VISIBLE);
            }
            sViewHolder = this;
            play.setVisibility(View.INVISIBLE);
            mTitle.setVisibility(View.INVISIBLE);
            background.setVisibility(View.INVISIBLE);
            if (sMediaPlayer.isPlaying()) {
                sMediaPlayer.stop();
            }
            sMediaPlayer.reset();
            sMediaPlayer.setDisplay(mSurfaceView.getHolder());
            if (video_url != null) {
                try {
                    sMediaPlayer.setDataSource(video_url);
                    sMediaPlayer.prepareAsync();
                    sIsPlayingPosition = getLayoutPosition();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

}
