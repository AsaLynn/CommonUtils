package com.zcommon.lib;

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

    /*    */
//    /**
//     * 在宿主Activity或Fragment可能被杀死的情景，优先使用此方法，比如分享后的回调
//     *
//     * @param hint
//     */
/*
    public static void showToast(final String hint) {
        if (TextUtils.isEmpty(hint))
            return;
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                onShowToast(AfkApp.getAppContext(), hint, 0);
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }*/

//    public static Toast showToast1(Context context, @StringRes int stringResID) {
//        return showToast1(context, context.getResources().getString(stringResID));
//    }

//    public static Toast showToast1(Context context, String hint) {
//        return onShowToast(context, hint, 0);
//    }

/*    private static Toast onShowToast(Context context, String msg, int type) {
        Toast toast = new Toast(context);
        View view = LayoutInflater.from(context).inflate(getLayRes(type), null);
        TextView content = view.findViewById(R.id.tv_content);
        FrameLayout.LayoutParams pm = (FrameLayout.LayoutParams) content.getLayoutParams();
        pm.width = ScreenUtils.getScreenWidth(context);
        view.setLayoutParams(pm);
        content.setText(msg);
        toast.setView(view);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }*/

/*    private static int getLayRes(int type) {
        switch (type) {
            case 1:
                return R.layout.view_custom_toast_01;
            default:
            case 0:
                return R.layout.view_custom_toast;
        }
    }*/


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
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
        } else {
            mToast.setText(msg);
            mToast.show();
        }

    }

    private static Toast mToast;


}
