package com.liantuo.baselib.util;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;


public class DialogUtil {

    private static Context mContext = null;
    private static ProgressDialog mProgressDialog = null;

    public static void showProgress(Context context, String message) {
        showProgressDialog(context, "", message);
    }

    public static void showProgressDialog(final Context context, final String title, final String message) {
        if (null == context) {
            return;
        }

        mContext = context;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            hideProgress();
            mProgressDialog = ProgressDialog.show(context, title, message,
                    true);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                    mProgressDialog = ProgressDialog.show(context, title, message,
                            true);
                }
            });
        }


    }

    public static void hideProgress() {
        if (null == mContext) {
            return;
        }

        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (mProgressDialog != null && mProgressDialog.isShowing())
                mProgressDialog.dismiss();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });
        }
    }

    public static void showDialog(final Context context, final String title,
                                  final String message, final OnDialogCallBack callBack) {

        if (Looper.myLooper() == Looper.getMainLooper()) {

            new AlertDialog.Builder(context).setTitle(title).setMessage(message)
                    .setPositiveButton("确定", new OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            callBack.onSure();
                            dialog.dismiss();
                        }
                    }).setNegativeButton("取消", new OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callBack.onCancel();
                    dialog.dismiss();
                }
            }).show();

        } else {

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {

                    new AlertDialog.Builder(context).setTitle(title).setMessage(message)
                            .setPositiveButton("确定", new OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    callBack.onSure();
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("取消", new OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            callBack.onCancel();
                            dialog.dismiss();
                        }
                    }).show();

                }
            });
        }


    }

    public interface OnDialogCallBack {
        void onSure();

        void onCancel();
    }
}
