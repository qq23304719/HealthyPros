package com.aorise.yx.healthypro.baseutils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by Administrator on 2016/9/1.
 */
public class MyActivity extends AppCompatActivity {
    public Dialog dialog;
    private Toast toast;
    public static int screenW, screenH;
    //--------------------沉浸式状态栏------------------
    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }

    }
    /** * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * @param color 状态栏颜色值 * @return 状态栏矩形条 */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }
    /** * 使状态栏透明 * <p> * 适用于图片作为背景的界面,此时需要图片填充到状态栏 * * @param activity 需要设置的activity */
    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

        // -----------------生命周期的管理，监控-------------------------------------------------
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            LogUtil.d(this, "onCreate");
            screenW = getWindowManager().getDefaultDisplay().getWidth();
            screenH = getWindowManager().getDefaultDisplay().getHeight();
        }

        @Override
        protected void onDestroy() {
            // TODO Auto-generated method stub
            super.onDestroy();
            LogUtil.d(this, "onDestroy");

        }

        @Override
        protected void onRestart() {
            // TODO Auto-generated method stub
            super.onRestart();
            LogUtil.d(this, "onRestart");
        }

        @Override
        protected void onStart() {
            // TODO Auto-generated method stub
            super.onStart();
            LogUtil.d(this, "onStart");
        }

        @Override
        protected void onResume() {
            // TODO Auto-generated method stub
            super.onResume();
            LogUtil.d(this, "onResume");
        }

        @Override
        protected void onPause() {
            // TODO Auto-generated method stub
            super.onPause();
            LogUtil.d(this, "onPause");
        }

        @Override
        protected void onStop() {
            // TODO Auto-generated method stub
            super.onStop();
            LogUtil.d(this, "onStop");
        }

        // -----------------Activity跳转及结束-------------------------------------------------------
        protected void startActivity(Class<?> targetClass) {//普通方式跳转activity
            Intent intent = new Intent(this, targetClass);
            startActivity(intent);
        }
        //需先将数据打包到bundle里  然后将bundle传入此方法
        protected void startActivity(Class<?> targetClass, Bundle bundle) {
            //跳转activity并 传递数据
            Intent intent = new Intent(this, targetClass);
            intent.putExtras(bundle);
            startActivity(intent);
        }


        /**
         * 切换Activity动画效果
         * overridePendingTransition(R.anim.fade, R.anim.hold);//淡入淡出
         * overridePendingTransition(R.anim.my_scale_action,R.anim.my_alpha_action);//放大淡出
         * overridePendingTransition(R.anim.scale_rotate,R.anim.my_alpha_action);//转动淡出1
         * overridePendingTransition(R.anim.scale_translate_rotate,R.anim.my_alpha_action);//转动淡出2
         * overridePendingTransition(R.anim.scale_translate,R.anim.my_alpha_action);//左上角展开淡出效果
         * overridePendingTransition(R.anim.hyperspace_in,R.anim.hyperspace_out);//压缩变小淡出效果
         * overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);//右往左推出效果
         * overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);//下往上推出效果
         * overridePendingTransition(R.anim.slide_left,R.anim.slide_right);//左右交叉效果
         * overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);//缩小效果
         * overridePendingTransition(R.anim.slide_up_in,R.anim.slide_down_out);//上下文交错
         */
        protected void startActivity(Class<?> targetClass, int inAnimID, int outAnimID) {
            Intent intent = new Intent(this, targetClass);
            this.startActivity(intent);
            this.overridePendingTransition(inAnimID, outAnimID);
        }

        protected void startActivity(Class<?> targetClass, int inAnimID, int outAnimID, Bundle bundle) {
            Intent intent = new Intent(this, targetClass);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(inAnimID, outAnimID);
        }

        @Override
        public void finish() {
            super.finish();
        }


//----------------------------隐藏软键盘-----------------------------------
    //当有edittext时 点击空白区域即可关闭软键盘， 集成即可  不用调用方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
    @Override
       public boolean dispatchTouchEvent(MotionEvent ev) {
                if (ev.getAction() == MotionEvent.ACTION_DOWN) {

                         // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
                         View v = getCurrentFocus();

                         if (isShouldHideInput(v, ev)) {
                                 hideSoftInput(v.getWindowToken());
                             }
                     }
                 return super.dispatchTouchEvent(ev);
             }

        /**
           * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
           *
           * @param v
           * @param event
           * @return
           */
         public boolean isShouldHideInput(View v, MotionEvent event) {
                 if (v != null && (v instanceof EditText)) {
                         int[] l = { 0, 0 };
                         v.getLocationInWindow(l);
                         int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                                 + v.getWidth();
                         if (event.getX() > left && event.getX() < right
                                 && event.getY() > top && event.getY() < bottom) {                // 点击EditText的事件，忽略它。
                                 return false;
                             } else {
                                 return true;
                             }
                     }
                 // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
                 return false;
             }

                 /**
           * 多种隐藏软件盘方法的其中一种
           *
           * @param token
           */
         public void hideSoftInput(IBinder token) {
                 if (token != null) {
                         InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                         im.hideSoftInputFromWindow(token,
                                         InputMethodManager.HIDE_NOT_ALWAYS);
                     }
             }

    public void setBsetBitmap(Bitmap bitmap, ImageView imageView) {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        //计算最佳缩放倍数
               /*float scaleX = (float) width / bitmap.getWidth();
               float scaleY = (float) height / bitmap.getHeight();
               float bestScale = scaleX < scaleY ? scaleX : scaleY;*/
        //计算最佳缩放倍数,以填充宽高为目标
        float scaleX = (float) width / bitmap.getWidth();
        float scaleY = (float) height / bitmap.getHeight();
        /*float bestScale = scaleX > scaleY ? scaleX : scaleY;*/
        //以填充高度的前提下，计算最佳缩放倍数
               float bestScale = (float) height / bitmap.getHeight();

        float subX = (width - bitmap.getWidth() * bestScale) / 2;
        float subY = (height - bitmap.getHeight() * bestScale) / 2;

        Matrix imgMatrix = new Matrix();
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        //缩放最佳大小
        imgMatrix.postScale(bestScale, bestScale);
        //移动到居中位置显示
        imgMatrix.postTranslate(subX, subY);
        //设置矩阵
        imageView.setImageMatrix(imgMatrix);

        imageView.setImageBitmap(bitmap);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, int dp)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    //获取SHA值----------------------------------------------------
    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }



    private void fullscreen(boolean enable) {
        if (enable) { //显示状态栏
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else { //隐藏状态栏
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(lp);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * @Title: cancelDialog
     * @Description: 取消dialog显示
     * @author hj
     */
    public void cancelDialog() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }
    public void showToast(String msg) {
        if (toast == null){
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }
}

