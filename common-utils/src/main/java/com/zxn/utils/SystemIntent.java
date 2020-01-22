package com.zxn.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 常用系统Intent的封装.
 * https://blog.csdn.net/lovoo/article/details/51757030
 * https://www.jb51.net/article/122754.htm
 * Created by zxn on 2019/5/16.
 */
public class SystemIntent {

    /**
     * 退出程序
     *<uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"/>
     * @param context
     */
    public static void exitApp(Context context) {
        jumpToHome(context);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static void jumpToCall(Context context, String tel) {
        try {
            Uri uri = Uri.parse("tel:" + tel);
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将应用退到桌面上,保留自身
     *
     * @param context
     */
    public static void jumpToHome(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(intent);
    }


    /**
     * 打开系统浏览器.
     *
     * @param context 上下文.
     * @param url     跳转的url.
     */
    public static void jumpToBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }


}
