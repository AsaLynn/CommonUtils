package com.zxn.utils

import android.os.Environment
import java.io.File

/**
 * Created by zxn on 2019/2/27.
 */
object SDUtil {
    /**
     * 获取sd卡的根目录.
     *
     * @return sd卡的根目录
     */
    val sDRoot: String
        get() = Environment.getExternalStorageDirectory().absolutePath

    /**
     * 获取指定目录在sd卡的路径.
     * @param pathName  路径名称
     * @return  目录d卡的完整路径.
     */
    fun getPathOnSD(pathName: String): String {
        return sDRoot + File.separator + pathName
    }
}