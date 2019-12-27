package com.zxn.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 吐司工具类
 * Created by zxn on 2018/12/27.
 */
public class ZToastUtils {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void showToast(String hint) {
        if (null != mContext) {
            Toast.makeText(mContext, hint, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(@StringRes int stringResID) {
        if (null != mContext) {
            Toast.makeText(mContext, mContext.getResources().getString(stringResID), Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(Context context, String hint) {
        Toast.makeText(context, hint, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, @StringRes int stringResID) {
        showToast(context, context.getResources().getString(stringResID));
    }


    public static Toast showCustomToast(Context context, String hint) {
        Toast toast = Toast.makeText(context, hint, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, -150);
        toast.show();
        return toast;
    }

    /**
     * 弹出吐司!
     *
     * @param msg 弹出内容.
     */
    public static void toast(String msg) {
        Toast mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

}
