package com.liantuo.baselib.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtil {

    private static Toast toast = null;

    public static void showToast(Context context, String msg) {
        if (!TextUtils.isEmpty(msg))
            showOnUiThread(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String msg, int duration) {
        if (!TextUtils.isEmpty(msg))
            showOnUiThread(context, msg, duration);
    }

    private static void showOnUiThread(final Context context, final String msg, final int duration) {
        if (null == context) {
            return;
        }

        if (Looper.myLooper() == Looper.getMainLooper()) {
            show(context, msg, duration);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    show(context, msg, duration);
                }
            });
        }
    }

    private static void show(Context context, String msg, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static void showCustomToast(Context context, String msg, int duration) {

//        if (null != context) {
//            TextView  textView = null;
//            if (toast == null) {
//                toast = new Toast(context);
//                LayoutInflater inflater = LayoutInflater.from(context);
//                View layout = inflater.inflate(R.layout.toast, null);
//                textView = (TextView) layout.findViewById(R.id.toast_tv_msg);
//                textView.setText(msg);
//
//                toast.setGravity(Gravity.CENTER, 0,
//                        BaseApplication.getInstance().screenH / 4);
//                toast.setDuration(duration);
//                toast.setView(layout);
//            }else {
//                textView.setText(msg);
//            }
//            toast.show();
//        }
    }

}
