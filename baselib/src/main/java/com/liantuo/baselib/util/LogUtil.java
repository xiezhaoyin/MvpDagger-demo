package com.liantuo.baselib.util;

import android.util.Log;


/**
 * Created by dell on 2017/9/1.
 */
public class LogUtil {

    private final static String I_TAG = "xzy";

//    private final static boolean isWrite = BuildConfig.DEBUG;
    private final static boolean isWrite = true;

    public static void d(String tag, String log) {
        if (isWrite)
            Log.d(I_TAG, tag + "  -->>  " + log + "  <<--  ");
    }

    public static void w(String tag, String log) {
        if (isWrite)
            Log.w(I_TAG, tag + "  ！！！  " + log + "  ！！！ ");
    }

    public static void e(String tag, String log) {
        if (isWrite)
            Log.e(I_TAG, tag + "  X X X  " + log + "  X X X  ");
    }
}
