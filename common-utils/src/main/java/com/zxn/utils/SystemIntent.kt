package com.zxn.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Process

/**
 * 常用系统Intent的封装.
 * https://blog.csdn.net/lovoo/article/details/51757030
 * https://www.jb51.net/article/122754.htm
 * Created by zxn on 2019/5/16.
 */
object SystemIntent {
    /**
     * 跳转到拨号页面.
     *
     * @param context Context
     * @param tel     String
     */
    fun jumpToDial(context: Context, tel: String) {
        try {
            val uri = Uri.parse("tel:$tel")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 退出程序
     * uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"
     *
     * @param context   Context
     */
    fun exitApp(context: Context) {
        jumpToHome(context)
        Process.killProcess(Process.myPid())
        System.exit(0)
    }

    /**
     * 跳转到拨号页面.
     *
     * @param context Context
     */
    fun jumpToCallButton(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_CALL_BUTTON)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 跳转进行拨打电话.
     *
     * @param context Context
     * @param tel     String
     */
    @SuppressLint("MissingPermission")
    fun jumpToCall(context: Context, tel: String) {
        try {
            val uri = Uri.parse("tel:$tel")
            val intent = Intent(Intent.ACTION_CALL, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 将应用退到桌面上,保留自身
     *
     * @param context Context
     */
    fun jumpToHome(context: Context) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addCategory(Intent.CATEGORY_HOME)
        context.startActivity(intent)
    }

    /**
     * 打开系统浏览器.
     *
     * @param context 上下文.
     * @param url     跳转的url.
     */
    fun jumpToBrowser(context: Context, url: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }
}