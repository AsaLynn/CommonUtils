package com.zxn.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * 吐司工具类
 * Created by zxn on 2018/12/27.
 */
object ZToastUtils {
    private var mContext: Context? = null
    fun init(context: Context?) {
        mContext = context
    }

    fun showToast(hint: String?) {
        if (null != mContext) {
            Toast.makeText(mContext, hint, Toast.LENGTH_SHORT).show()
        }
    }

    fun showToast(@StringRes stringResID: Int) {
        if (null != mContext) {
            Toast.makeText(mContext, mContext!!.resources.getString(stringResID), Toast.LENGTH_SHORT).show()
        }
    }

    fun showToast(context: Context?, hint: String?) {
        Toast.makeText(context, hint, Toast.LENGTH_SHORT).show()
    }

    fun showToast(context: Context, @StringRes stringResID: Int) {
        showToast(context, context.resources.getString(stringResID))
    }

    fun showCustomToast(context: Context?, hint: String?): Toast {
        val toast = Toast.makeText(context, hint, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, -150)
        toast.show()
        return toast
    }

    /**
     * 弹出吐司!
     *
     * @param msg 弹出内容.
     */
    fun toast(msg: String?) {
        val mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG)
        mToast.setGravity(Gravity.CENTER, 0, 0)
        mToast.show()
    }
}