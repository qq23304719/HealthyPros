package com.aorise.yx.healthypro.baseutils;

import android.util.Log;

/**
 * Created by Administrator on 2016/9/1.
 */
public class LogUtil {
    public static final String TAG = "UU财富";
    public static boolean isOpenDebug = true; // 是否开启debug logcat
    // 当 代码编辑时期 可以设置为 true   项目编辑完成发布时 改成false

    /**
     * 推荐使用。用调用者类名做为tag值，进行日志输出
     *
     * @param obj
     *            　一般传入 this
     * @param msg
     *            日志信息
     *
     * @see {@link #isOpenDebug}
     */
    public static void d(Object obj, String msg) { //debug
        if (isOpenDebug) {
            Log.d(obj.getClass().getSimpleName(), msg);
            // tag  日志输出的标签     msg  内容

        }
    }

    /**
     * 推荐使用。用调用者类名做为tag值，进行日志输出
     *
     * @param obj
     *            　一般传入 this
     * @param msg
     *            日志信息
     * @param throwable
     *            异常对象,没有可传入null, or {@link #d(Object, String)}
     *
     * @see {@link #isOpenDebug}
     */
    public static void d(Object obj, String msg, Throwable throwable) {
        if (isOpenDebug) {
            Log.d(obj.getClass().getSimpleName(), msg, throwable);

        }
    }
}
