package com.zxn.utils

/**
 * Copyright(c) ${}YEAR} ZhuLi co.,Ltd All Rights Reserved.
 *
 * @className: ViewUtils$
 * @description: TODO 类描述
 * @version: v0.0.1
 * @author: zxn < a href=" ">zhangxiaoning@17biyi.com</ a>
 * @date: 2021/2/1$ 11:57$
 * @updateUser: 更新者：
 * @updateDate: 2021/2/1$ 11:57$
 * @updateRemark: 更新说明：
 * @version: 1.0
 * */
object ViewUtils {

    /**
     * 两次点击按钮之间的点击间隔不能少于1000毫秒
     */
    private val MIN_CLICK_DELAY_TIME = 800L

    /**
     * 最后一次点击的时间
     */
    private var mLastClickTime: Long = -1

    /**
     * 是否为快速点击
     *
     * @return true:快速点击  false:非快速点击
     */
    fun isTooFastClick(): Boolean {
        val flag: Boolean
        val curClickTime = System.currentTimeMillis()
        flag = curClickTime - mLastClickTime <= MIN_CLICK_DELAY_TIME
        mLastClickTime = curClickTime
        return flag
    }
}