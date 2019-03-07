package com.zcommon.lib;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * 系统UI操作工具类
 * Created by zxn on 2019/3/6.
 */
public class UIUtils {

    private static Context mContext;

    /**
     * 工具类初始化.一般在程序入口初始化一次.
     *
     * @param context 上下文对象
     */
    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 获取color.xml中的颜色
     *
     * @param context 上下文
     * @param id      颜色id,例如R.id.c_ffffff
     * @return 颜色值
     */
    public static int getColor(Context context, @ColorRes int id) {
        return context.getResources().getColor(id);
    }

    /**
     * 获取color.xml中的颜色
     *
     * @param view 控件
     * @param id   颜色id,例如R.id.c_ffffff
     * @return 颜色值
     */
    public static int getColor(View view, @ColorRes int id) {
        return view.getResources().getColor(id);
    }

    /**
     * 获取color.xml中的颜色
     *
     * @param id 颜色id,例如R.id.c_ffffff
     * @return 颜色值
     */
    public static int getColor(@ColorRes int id) {
        isNull();

        return mContext.getResources().getColor(id);
    }


    /**
     * 获取Context
     *
     * @return mContext
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * Returns a localized formatted string from the application's package's
     * default string table, substituting the format arguments as defined in
     * {@link java.util.Formatter} and {@link java.lang.String#format}.
     *
     * @param resId      Resource id for the format string
     * @param formatArgs The format arguments that will be used for
     *                   substitution.
     * @return The string data associated with the resource, formatted and
     * stripped of styled text information.
     */
    public static String getString(@StringRes int resId, Object... formatArgs) {
        isNull();
        //getString(@StringRes int resId, Object... formatArgs)
        return mContext.getString(resId, formatArgs);
    }

    /**
     * Returns a localized string from the application's package's
     * default string table.
     *
     * @param resId Resource id for the string
     * @return The string data associated with the resource, stripped of styled
     * text information.
     */
    public static String getString(@StringRes int resId) {
        isNull();
        return mContext.getString(resId);
    }

    //-------------------------一下方法为内部使用-----------------------------

    /**
     * mContext 是否为null.
     */
    private static void isNull() {
        if (null == mContext) {
            throw new RuntimeException("null == mContext,should call init()");
        }
    }
}
