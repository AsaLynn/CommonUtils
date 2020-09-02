package com.zxn.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by zxn on 2019/2/27.
 */
public class SDUtil {

    /**
     * 获取sd卡的根目录.
     *
     * @return sd卡的根目录
     */
    public static String getSDRoot() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }


    /**
     * 获取指定目录在sd卡的路径.
     * @param pathName  路径名称
     * @return  目录d卡的完整路径.
     */
    public static String getPathOnSD(String pathName) {
        return getSDRoot() + File.separator + pathName;
    }

}
